package qnelldo.sixtimes.security.oauth2;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import qnelldo.sixtimes.domain.user.mysql.entity.User;
import qnelldo.sixtimes.domain.user.mysql.service.UserService;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOAuth2User(userRequest, oAuth2User);
    }

    // 새로운 메소드: userId를 기반으로 OAuth2 사용자를 로드
    public CustomOAuth2User loadUserByUserId(String userId) {
        User user = userService.findByProviderAndProviderId("google", userId)
                .orElseThrow(() -> new OAuth2AuthenticationException("User not found"));
        return new CustomOAuth2User(
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                user.toAttributes(), // User 엔티티의 속성들을 맵으로 변환하여 사용
                "name"
        );
    }

    // OAuth2 사용자를 처리하는 메소드
    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        // User 엔티티를 데이터베이스에서 찾거나 새로 생성
        User user = userService.findByProviderAndProviderId(provider, providerId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setProvider(provider);
                    newUser.setProviderId(providerId);
                    newUser.setName(name);
                    newUser.setEmail(email);
                    return userService.saveUser(newUser);
                });

        return new CustomOAuth2User(
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "name"
        );
    }
}
