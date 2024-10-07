package com.longpc.tuto.api.authen.service;

import com.longpc.tuto.api.authen.model.AccountPrincipleModel;
import com.longpc.tuto.api.authen.model.SignInResponseModel;
import com.longpc.tuto.api.constant.ExceptionEnum;
import com.longpc.tuto.api.constant.TypeEnum;
import com.longpc.tuto.api.data.entity.authen.Account;
import com.longpc.tuto.api.data.entity.authen.Party;
import com.longpc.tuto.api.data.manager.AccountManager;
import com.longpc.tuto.api.data.manager.PartyManager;
import com.longpc.tuto.api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Long PC
 * 28/12/2023| 15:50 | 2023
 **/
@Service
public class AuthenService extends BaseService implements UserDetailsService {
    @Autowired
    AccountManager accountManager;
    @Autowired
    PartyManager partyManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtEncoder encoder;

    public SignInResponseModel signInPassword(String username, String password) {
        AccountPrincipleModel accountPrincipleModel = getAccountPrincipleByUsername(username);
        if (!passwordEncoder.matches(password, accountPrincipleModel.getPassword())) {
            throw new IllegalArgumentException(ExceptionEnum.WRONG_USERNAME_PASSWORD.name());
        }
        if (accountPrincipleModel.getStatus() == TypeEnum.Status.IN_ACTIVE.getValue()) {
            throw new IllegalArgumentException(ExceptionEnum.ACCOUNT_INACTIVE.name());
        }
        Instant now = Instant.now();
        long expiry = 60 * 60 * 24;
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("longpc")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(accountPrincipleModel.getPartyId())
                .build();
        // @formatter:on
        String token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return SignInResponseModel.builder()
                .authorities(new ArrayList<>(accountPrincipleModel.getAuthorities()))
                .partyId(accountPrincipleModel.getPartyId())
                .token(token).build();
    }

    public void signOut() {

    }

    public Account signUp(String username, String password, String email, String phone, Set<String> roles) {
        String encodedPassword = passwordEncoder.encode(password);
        // Khoi tao Party
        Party party = new Party();
        party.setName(username);
        party.setEmail(email);
        party.setPhone(phone);
        party.setStatus(TypeEnum.Status.ACTIVE.getValue());
        party.setCreatedTime(new Date());
        Party rsParty = partyManager.save(party);
        // khoi tao account
        Account account = new Account();
        account.setUsername(username);
        account.setStatus(TypeEnum.Status.ACTIVE.getValue());
        account.setRole(roles);
        account.setPassword(encodedPassword);
        account.setPartyId(rsParty.getId());
        account.setCreatedTime(new Date());
        return accountManager.save(account);
    }

    public AccountPrincipleModel getAccountPrincipleByUsername(String username) {
        Account account = accountManager.getByUsername(username);
        if (ObjectUtils.isEmpty(account)) {
            throw new IllegalArgumentException(ExceptionEnum.WRONG_USERNAME_PASSWORD.name());
        }
        return AccountPrincipleModel.create(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAccountPrincipleByUsername(username);
    }

    public void createAdminAccount() {
        Account account = accountManager.getByUsername("admin");
        if (ObjectUtils.isEmpty(account)) {
            Account accountSignUp = signUp("admin", "admin", "    ", "    ", Set.of(TypeEnum.Role.ADMIN.name()));
            System.out.printf("Account admin created: %s", accountSignUp.getUsername() + " " + "admin");
        }
    }

    public void createUserAccount() {
        Account account = accountManager.getByUsername("user");
        if (ObjectUtils.isEmpty(account)) {
            Account accountSignUp = signUp("user", "user", "    ", "    ", Set.of(TypeEnum.Role.USER.name()));
            System.out.printf("Account user created: %s", accountSignUp.getUsername() + " " + "user");
        }
    }

    public boolean checkExistUsername(String username) {
        return accountManager.checkExistUsername(username);
    }
}
