package com.example.demo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    admin(1), // Vai trò quản trị viên, có quyền cao nhất trong hệ thống.
    user(2); // Vai trò người dùng bình thường, có quyền hạn giới hạn.
    public final long value;
}