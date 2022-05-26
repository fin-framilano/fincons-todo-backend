package fincons.todo.backend.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fincons.todo.backend.entities.User;
import fincons.todo.backend.entities.dto.UserDto;
import fincons.todo.backend.repositories.UserRepository;
import fincons.todo.backend.services.UserService;
import fincons.todo.backend.utils.UserUtils;


@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(
			@Qualifier("userRepository") UserRepository userRepository
	) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDto findByMail(String mail) {
		User userVo = this.userRepository.findByMail(mail);
		if (userVo != null) return UserUtils.fromVOtoDTO(userVo);
		else return null;
	}

	@Override
	public Long create(UserDto userDto) {
		userDto.setId(Long.valueOf(0));
		User userVo = UserUtils.fromDTOtoVO(userDto);
		this.userRepository.save(userVo);
		return null;
	}

}
