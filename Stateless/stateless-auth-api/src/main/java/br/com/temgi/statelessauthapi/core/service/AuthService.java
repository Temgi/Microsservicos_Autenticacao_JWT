package br.com.temgi.statelessauthapi.core.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.temgi.statelessauthapi.core.dto.AuthRequest;
import br.com.temgi.statelessauthapi.core.dto.TokenDTO;
import br.com.temgi.statelessauthapi.core.repository.UserRepository;
import br.com.temgi.statelessauthapi.infra.exception.ValidationException;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
@AllArgsConstructor
public class AuthService {

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;

	public TokenDTO login(AuthRequest request) {
		var user = repository.findByUsername(request.username())
				.orElseThrow(() -> new ValidationException("User not found!"));
		var accessToken = jwtService.createToken(user);
		validatePassword(request.password(), user.getPassword());
		return new TokenDTO(accessToken);
	}

	private void validatePassword(String rawPassword, String encodedPassword) {
		if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
			throw new ValidationException("The password is incorrect!");
		}
	}

	public TokenDTO validateToken(String accessToken) {
		validateExistingToken(accessToken);
		jwtService.validateAccessToken(accessToken);
		return new TokenDTO(accessToken);
	}
	
    private void validateExistingToken(String accessToken) {
        if (isEmpty(accessToken)) {
            throw new ValidationException("The access token must be informed!");
        }
    }

}
