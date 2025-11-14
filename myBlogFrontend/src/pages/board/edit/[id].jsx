// pages/board/edit/[id].jsx
import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import PostForm from './../../../components/PostForm';
import { postsService } from './../../../services/postService';

const EditPost = () => {
  const router = useRouter();
  const { id } = router.query;
    // 초기 상태에 임시 데이터를 설정합니다.
    const [postData, setPostData] = useState({
        postId:'',  
        userId:'',
        title: '',
        status:'',
        viewCount: '',
        content: ''
      });

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await postsService.getPost(id);
        console.log(response.data)
        setPostData(response.data);
      } catch (err) {
        console.log('게시글을 불러오는 데 실패했습니다.');
        console.error(err);
      }
    };

    
    if(router.isReady  && id){
      fetchPost()
    }
  }, [id, router.isReady]);

  return (
    <div>
      {postData && <PostForm postId={id} mode="edit" initialData={postData} />}
    </div>
  );
};

export default EditPost;
