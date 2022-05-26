package fincons.todo.backend.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fincons.todo.backend.entities.User;
import fincons.todo.backend.entities.dto.LoginDto;
import fincons.todo.backend.entities.dto.UserDto;
import fincons.todo.backend.repositories.UserRepository;
import fincons.todo.backend.services.SecurityService;
import fincons.todo.backend.utils.UserUtils;

@Service
public class SecurityServiceImpl implements SecurityService {

	private UserRepository userRepository;
	
	public SecurityServiceImpl(
			@Qualifier("userInMemoryRepository") UserRepository userRepository
			) {
		this.userRepository = userRepository;
	}

	/**
	 * Servizio di login che dato un LoginDto restituisce uno UserDto
	 */
	public UserDto findUserByMailAndPassword(LoginDto loginDto) {
		User userDvo = userRepository.findUserByMailAndPassword(loginDto.getMail(), loginDto.getPassword());
		if (userDvo == null) return null;
		UserDto userDto = UserUtils.fromVOtoDTO(userDvo);
		return userDto;
	}

}
