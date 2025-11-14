// postService.js

import { POST_API } from './../api/endpoints';
import axiosInstance from './../api/axiosInstance';

export const postsService = {
  // 게시글 조회 (GET /posts/{postId})
  getPost: (postId) => {
    return axiosInstance.get(`${POST_API}/${postId}`);
  },

  // 게시글 수정 (PUT /posts/{postId})
  updatePost: (postId, postData) => {
    return axiosInstance.put(`${POST_API}/${postId}`, postData);
  },

  // 게시글 삭제 (DELETE /posts/{postId})
  deletePost: (postId) => {
    return axiosInstance.delete(`${POST_API}/${postId}`);
  },

  // 새로운 게시글 생성 (POST /posts)
  createPost: (postData) => {
    return axiosInstance.post(POST_API, postData);
  }
};

