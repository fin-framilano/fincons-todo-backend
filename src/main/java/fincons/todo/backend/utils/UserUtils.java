package fincons.todo.backend.utils;

import fincons.todo.backend.entities.User;
import fincons.todo.backend.entities.dtos.UserDto;

public class UserUtils {

	public static UserDto fromVOtoDTO(User u) {
		UserDto userDto = new UserDto();
		userDto.setId(u.getId());
		userDto.setMail(u.getMail());
		userDto.setPassword(u.getPassword());
		return userDto;
	}
	
	public static User fromDTOtoVO(UserDto u) {
		User user = new User();
		user.setId(u.getId());
		user.setMail(u.getMail());
		user.setPassword(u.getPassword());
		return user;
	}

}
