package com.haha.git;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aniruddha.g on 30/08/16.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GithubConfig {


    String apiUrl;


    String login;


    String token;

    String commaSeperatedAllowedFileTypes;

    public Set<String> getAllowedFileExtensions()  {
        return new HashSet<String>(Arrays.asList(commaSeperatedAllowedFileTypes.split(",")));
    }
}