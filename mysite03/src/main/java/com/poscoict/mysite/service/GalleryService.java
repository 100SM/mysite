package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.GalleryRepository;
import com.poscoict.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	GalleryRepository galleryRepository;

	// 이미지 전체 리스트 가져오기
	public List<GalleryVo> getGalleryList() {
		return galleryRepository.findAll();
	}

	// 이미지 업로드
	public Boolean saveImage(GalleryVo galleryVo) {
		return galleryRepository.insert(galleryVo) == 1;
	}

	// 이미지 삭제
	public Boolean removeImage(Long no) {
		return galleryRepository.delete(no);
	}
}