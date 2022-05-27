package fincons.todo.backend.services;

import fincons.todo.backend.entities.dtos.UserDto;

public interface UserService {

	UserDto findByMail(String mail);

	Long create(UserDto userDto);

}
