package com.hand.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hand.dao.FilmMapper;
import com.hand.entity.Film;
import com.hand.service.FilmService;

@Service("filmService")
public class FilmServiceImpl implements FilmService {
	@Autowired
	FilmMapper filmMapper;

    public int insertFiml(Film film) {
       int i=filmMapper.insertSelective(film);
        return i;
    }

    

}
