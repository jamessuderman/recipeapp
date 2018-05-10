/*
 *   GEODynamics
 *   Author - James Suderman
 *   Date - 05/10/2018
 */

package com.sudee.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
