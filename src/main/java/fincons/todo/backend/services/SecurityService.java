package fincons.todo.backend.services;

import fincons.todo.backend.entities.dto.LoginDto;
import fincons.todo.backend.entities.dto.UserDto;

public interface SecurityService {

	UserDto findUserByMailAndPassword(LoginDto loginDto);

}
