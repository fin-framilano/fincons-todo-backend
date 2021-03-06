package fincons.todo.backend.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fincons.todo.backend.entities.User;
import fincons.todo.backend.entities.dtos.UserDto;
import fincons.todo.backend.repositories.UserRepository;
import fincons.todo.backend.services.UserService;
import fincons.todo.backend.utils.UserUtils;


@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(
			//@Qualifier("userInMemoryRepository") UserRepository userRepository
			@Qualifier("userRepository") UserRepository userRepository
	) {
		this.userRepository = userRepository;
	}
	
	/**
	 * Restituisco uno User in base alla sua mail, null se la mail non esiste
	 * @param mail dell'utente da trovare
	 * @return l'utente trovato
	 */
	public UserDto findByMail(String mail) {
		User userVo = this.userRepository.findByMail(mail);
		if (userVo != null) return UserUtils.fromVOtoDTO(userVo);
		else return null;
	}

	/**
	 * @param userDto l'utente da creare
	 * @return id associato all'utente creato
	 */
	public Long create(UserDto userDto) {
		userDto.setId(Long.valueOf(0));
		User userVo = UserUtils.fromDTOtoVO(userDto);
		this.userRepository.save(userVo);
		return null;
	}

}
