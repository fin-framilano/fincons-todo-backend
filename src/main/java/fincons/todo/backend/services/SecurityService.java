package fincons.todo.backend.services;

import fincons.todo.backend.entities.dtos.LoginDto;
import fincons.todo.backend.entities.dtos.UserDto;

public interface SecurityService {

	UserDto findUserByMailAndPassword(LoginDto loginDto);

}
