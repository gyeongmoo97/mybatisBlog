import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import { Button, Container, Paper, Typography } from '@mui/material';
import { postsService } from './../../services/postService';

const PostDetailPage = () => {
  const router = useRouter();
  const { id } = router.query;
  const [post, setPost] = useState({ title: '', content: '', image:'' });

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await postsService.getPost(id);
        console.log(response.data)
        setPost(response.data);
      } catch (err) {
        console.log('게시글을 불러오는 데 실패했습니다.');
        console.error(err);
      }
    };

    if(router.isReady){
      fetchPost()
    }
  }, [id, router.isReady]);

  // 메인 페이지로 돌아가는 함수
  const handleBack = () => {
    router.push('/');
  };

  // 게시물 수정 페이지로 이동하는 함수
  const handleEdit = () => {
    router.push(`./edit/${id}`);
  };

  if (!post) {
    return <div>Loading...</div>;
  }

  return (
    <Container component="main" maxWidth="md">
      <Paper elevation={3} sx={{ p: 3, mt: 10, mb: 3 }}>
        <Typography variant="h3" component="h1" gutterBottom>
          {post.title}
        </Typography>
        <Typography variant="body1" gutterBottom>
          {post.content} 
        </Typography>
        <Typography variant="body1" gutterBottom>
        Post id : {id} 
        </Typography>
        <Button variant="contained" color="primary" onClick={handleBack} sx={{ mt: 2 }}>
          메인 페이지로 돌아가기
        </Button>
        <Button variant="contained" color="secondary" onClick={handleEdit} sx={{ mt: 2, ml: 2 }}>
          게시물 수정
        </Button>
      </Paper>
    </Container>
  );
};

export default PostDetailPage;
