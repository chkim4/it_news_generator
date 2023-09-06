package com.ing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ing.service.MemberService;

/**
 * Spring Security 인증 방법 정의
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private PasswordEncoder pwEncoder;
    
    /**
     * 인증 방법 <br>
     * 1. 로그인 시: 사용자가 입력한 (login.jsp에서 보낸) email과 pass를 바탕으로 실행 <br>
     * 2. 로그인 이후 인증: 사용자의 memberId (기본키)를 통해 인증 <br>
     * 
     * @param authentication 사용자가 제출한 인증 정보 (여기 있는 정보를 확인하여 인증 여부를 결정함)
     * @return 인증 후 생성된 UsernamePasswordAuthenticationToken
     * @throws AuthenticationException - 존재하지 않는 계정, 암호가 틀림 등 사용자가 제출한 인증 정보를 통해 인증이 불가능할 경우 발생하는 예외
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        Boolean isLoginedBefore = false;
        
        int memberId = 0;
        
        // 이미 인증을 했는 지 확인 (isLoginedBefore)
        try {
            memberId = Integer.parseInt(username);
            isLoginedBefore = true;
        }
        catch (NumberFormatException e) {
        }
        
        // 이미 인증을 마친 상태에서 다시 인증할 때
        if (isLoginedBefore) {
            UserDetails loginedUser = memberService.loadById(memberId);
             
            if(loginedUser == null) {
                throw new BadCredentialsException("사용자 정보를 찾을 수 없습니다.");
            }
            
            else {
                return new UsernamePasswordAuthenticationToken(loginedUser, password);
            }
        }
        
        // 인증이 안 된 상태에서 인증할 때
        UserDetails user = memberService.loadByEmail(username);
             
        // 잘못된 정보로 로그인
        if (user == null) {
            throw new BadCredentialsException("사용자 정보를 찾을 수 없습니다.");
        }
        
        // 인증 이전에 로그인 시도할 때
        else if (!this.pwEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 찾을 수 없습니다.");
        }  

        return new UsernamePasswordAuthenticationToken(user, password);
    }

    /**
     * authenticate의 동작 여부 결정
     * 
     * @param authentication 사용자로부터 받은 인증 정보
     * @return authenticate 메소드 동작 여부를 결정하는 Boolean 값 
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}