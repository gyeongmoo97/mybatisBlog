import React, { useState, useEffect } from 'react';
import { Container, TextField, Button, Typography, Paper, Box, useTheme, useMediaQuery } from '@mui/material';
import { postsService } from './../services/postService';
import { useRouter } from 'next/router';

const PostForm = ({ postId, mode, initialData }) => {

  const router = useRouter(); // useRouter 훅을 사용하여 router 객체를 생성합니다.

  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down('sm'));
  const [postData, setPostData] = useState({ title: '', content: '',userId:'10' });
  //userId는 원래는 다른 곳에서 받아와야함 해당프로젝트는 로그인을 구현하지 않음으로 임의로 지정

  useEffect(() => {
    if (mode === 'edit' && initialData) {
      setPostData({...initialData, postId:postId});
    }
  }, [mode, initialData, postId]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setPostData({ ...postData, [name]: value });
  };

  const updatePost = async () => {
    try {
      const response = await postsService.updatePost(postId, postData);
      
      router.push(`/board/${postId}`);
    } catch (err) {
      alert('게시글 수정에 실패했습니다.');
      console.error(err);
    }
  };

  const createPost = async () => {
    try {
      const response = await postsService.createPost(postData);
      console.log(response.data)
      setPostData(response.data);
    } catch (err) {
      alert('게시글 생성에 실패했습니다.');
      console.error(err);
    }
  };

  //저장 버튼 클릭
  const handleSubmit = () => {
    // postData.title이 빈 문자열인 경우 알림을 보내고 함수 실행을 중단합니다.
    if (!(postData.title.trim())) {
      alert('제목을 입력해 주세요.');
      return;
    }
  
    // mode에 따른 분기 처리
    if (mode === 'edit' && postId) {
      console.log(postId)
      // setPostData({ ...postData, postId: postId });
      console.log('수정 데이터:', postData);
      updatePost()
    } else {
      console.log('새 게시물 데이터:', postData);
      createPost()
    }
  };

  return (
    <Container component="main" maxWidth="md" sx={{ my: 4 }}>
      <Paper elevation={6} sx={{ p: isSmallScreen ? 2 : 4, borderRadius: 2 }}>
        <Box display="flex" flexDirection="column" alignItems="center">
          <Typography variant={isSmallScreen ? 'h5' : 'h4'} component="h1" sx={{ mb: 3 }}>
            {mode === 'edit' ? '게시물 수정' : '새 게시물 작성'}
          </Typography>
          <form noValidate autoComplete="off" style={{ width: '100%' }}>
            <TextField
              fullWidth
              label="제목"
              name="title"
              value={postData.title}
              onChange={handleInputChange}
              margin="normal"
              variant="outlined"
            />
            <TextField
              fullWidth
              label="내용"
              name="content"
              value={postData.content}
              onChange={handleInputChange}
              margin="normal"
              multiline
              rows={isSmallScreen ? 4 : 6}
              variant="outlined"
            />
            <Box mt={2} width="100%" display="flex" justifyContent="flex-end">
              <Button
                variant="contained"
                color="primary"
                onClick={handleSubmit}
                sx={{ mt: 2, px: 4, py: isSmallScreen ? 1 : undefined }}
              >
                {mode === 'edit' ? '수정' : '저장'}
              </Button>
            </Box>
          </form>
        </Box>
      </Paper>
    </Container>
  );
};

export default PostForm;
