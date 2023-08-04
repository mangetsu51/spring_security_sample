package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class LoginUserService  implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	// コンストラクタインジェクション
	@Autowired
	public LoginUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	 // emailはユーザーから送信されるメールアドレスを想定
	public LoginUser loadUserByUsername(String email) throws UsernameNotFoundException {
		// emailによりデータベースからユーザ情報の取得
		User user = this.userRepository.findByEmail(email);
		
		// ユーザー情報が見つからない場合、例外を発生させる
		if(user == null) {
			throw new UsernameNotFoundException("ユーザーが見つかりません");
			
		}
		
		// ユーザ情報が見つかった場合は、UserDetailsを生成し返却
		return new LoginUser(user);
	}
}
