package src.main.java.com.augx.user.service.impl;

import com.augx.auditor.model.User;
import com.augx.auditor.model.dto.UserDto;
import com.augx.auditor.repository.UserRepository;
import src.main.java.com.augx.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper = new ModelMapper();

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public UserDto createOrUpdateUser(UserDto userDto) {
        User user = mapDtoToUser(userDto);
        if (null == user.getId() || userRepository.findById(user.getId()).isPresent()) {
            return mapUserToDto(userRepository.save(user));
        }
        return null;
    }

    @Override
    public UserDto getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::mapUserToDto).orElse(null);
    }

    @Override
    public List<UserDto> getUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<User>>() {
        }.getType());
    }

    private User mapDtoToUser(UserDto userDto) {
        return null != userDto ? modelMapper.map(userDto, User.class) : null;
    }

    private UserDto mapUserToDto(User user) {
        return null != user ? modelMapper.map(user, UserDto.class) : null;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
