package com.example.loginservice.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.loginservice.Model.TaiKhoan;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {

    @Query(value = "{call SP_LOGIN_INFO(:username)}", nativeQuery = true)
    Map<String, Object> loadUser(@Param("username") String username);

    @Procedure(procedureName = "SP_UPDATE_PASSWORD_TK")
    void updatePassword(@Param("username") String username,
            @Param("password") String password);

    @Query(value = "{call SP_GET_EMAIL_TKSV(:username)}", nativeQuery = true)
    String getEmailSV(@Param("username") String username);

    @Query(value = "{call SP_GET_EMAIL_TKGV(:username)}", nativeQuery = true)
    String getEmailGV(@Param("username") String username);

    @Query(value = "{call SP_GET_ROLE_USERNAME(:username)}", nativeQuery = true)
    String getRoleTaiKhoan(@Param("username") String username);
}
