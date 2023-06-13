package com.example.service.impl;

import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import com.example.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {

        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints = new ArrayList<Map<Object, Object>>();
        int bookNumber = bookRepository.countBook();
        int userNumber = userRepository.countUser();
        map = new HashMap<Object, Object>();
        map.put("label", "Sách");
        map.put("y", bookNumber);
        dataPoints.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "Người dùng");
        map.put("y", userNumber);
        dataPoints.add(map);

        return list;
    }
}
