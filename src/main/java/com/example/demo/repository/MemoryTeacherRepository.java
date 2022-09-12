package com.example.demo.repository;

import com.example.demo.model.Teacher;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Repository
public class MemoryTeacherRepository implements TeacherRepository {
    private static Map<Long, Teacher> store=new HashMap<>();
    private static long sequence=0L;

    @Override
    public Teacher save(Teacher teacher){
        teacher.setId(++sequence);
        store.put(teacher.getId(), teacher);
        return teacher;
    }

    @Override
    public Optional<Teacher> findByEmail(String email){
            return Optional.ofNullable(store.get(email));
    }

    @Override
    public Optional<Teacher> findByNickNickName(String nickname){
        return store.values().stream()
                .filter(teacher->teacher.getNickName().equals(nickname))
                .findAny();
    }

    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }


}
