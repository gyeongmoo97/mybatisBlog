import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import { Home, Create, AccountCircle, Mail, Instagram, Twitter, LinkedIn, Facebook } from '@mui/icons-material';
import { AppBar, BottomNavigation, BottomNavigationAction, Button, Card, CardActionArea, CardContent, CardMedia, Chip, Container, Grid, IconButton, Link, Menu, Toolbar, Typography } from '@mui/material';
import { Box } from '@mui/system';
import { Menu as MenuIcon } from '@mui/icons-material';
import axiosInstance from '../api/axiosInstance';
import { POST_API } from '../api/endpoints';

function BlogHome() {

  const router = useRouter(); // useRouter 훅을 사용하여 router 객체를 생성합니다.

  const [value, setValue] = React.useState(0);
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  // API에서 게시물 리스트 가져오기
  useEffect(() => {
    const fetchPosts = async () => {
      try {
        setLoading(true);
        const response = await axiosInstance.get(POST_API);
        setPosts(response.data);
      } catch (error) {
        console.error('게시물을 불러오는데 실패했습니다:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, []);

  const [appBarHeight, setAppBarHeight] = useState(0);

  useEffect(() => {
    // AppBar 요소를 선택합니다.
    const appBar = document.querySelector('.MuiAppBar-root');
    if (appBar) {
      // AppBar의 높이를 상태에 저장합니다.
      setAppBarHeight(appBar.clientHeight);
    }
  }, []); // 의존성 배열이 빈 배열이므로, 컴포넌트 마운트 시에만 실행됩니다.

  // board/[id] 경로로 라우팅합니다.
  const handlePostClick = (post) => {
    router.push(`/board/${post.postId}`);
  };

  // 새 글 작성 페이지로 이동하는 함수
  const handleCreatePost = () => {
    router.push('/board/create');
  };


  return (
    <div>

      {/* app bar */}
      <AppBar position="fixed">
        <Toolbar>
          <Grid container spacing={1} alignItems="center" justifyContent="center">
            <Grid item xs={12} sm={true}>
              <Typography variant="h6" component="div" sx={{ flexGrow: 1, textAlign: 'center' }}>
                호주의 블로그
              </Typography>
            </Grid>
            <Grid item xs={12} sm={true} container justifyContent="center">
              <Button color="inherit">홈</Button>
              <Button color="inherit">메뉴1</Button>
              <Button color="inherit">메뉴2</Button>
              <Button color="inherit">메뉴3</Button>
            </Grid>
            <Grid item>
              <Button color="inherit" onClick={handleCreatePost}>
                새 글 작성
              </Button>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>

    <div style={{ paddingTop: appBarHeight,  paddingBottom: appBarHeight }}>
      <Container>
        {loading ? (
          <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
            <Typography variant="h6">게시물을 불러오는 중...</Typography>
          </Box>
        ) : posts.length === 0 ? (
          <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
            <Typography variant="h6">게시물이 없습니다.</Typography>
          </Box>
        ) : (
          <Grid container spacing={3}>
            {posts.map(post => (
              <Grid item xs={12} sm={6} md={4} lg={3} key={post.postId}>
                <CardActionArea onClick={() => handlePostClick(post)}>
                  <Card sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                    {post.imageUrl && (
                      <CardMedia
                        component="img"
                        alt="미리보기 이미지"
                        height="140"
                        image={post.imageUrl}
                        sx={{ objectFit: 'cover' }}
                      />
                    )}
                    <Box flexGrow={1} display="flex" flexDirection="column">
                      <CardContent sx={{ flexGrow: 1 }}>
                        <Typography gutterBottom variant="h5" component="h2">{post.title}</Typography>
                        <Typography variant="body2" color="textSecondary" component="p">
                          {post.content ? post.content.substring(0, 100) : ''}
                          {post.content && post.content.length > 100 ? '...' : ''}
                        </Typography>
                        <Typography variant="caption" color="textSecondary" sx={{ mt: 1 }}>
                          조회수: {post.viewCount || 0}
                        </Typography>
                      </CardContent>
                      <Box p={1} display="flex" justifyContent="flex-start" flexWrap="wrap">
                        {post.categoryName && (
                          <Chip label={post.categoryName} sx={{ m: 0.5 }} />
                        )}
                      </Box>
                    </Box>
                  </Card>
                </CardActionArea>
              </Grid>
            ))}
          </Grid>
        )}
      </Container>
    </div>

      <footer style={{ textAlign: 'center', padding: '2em 0', background: '#333', color: 'white' }}>
  <Container>
    <Grid container spacing={3}>
      <Grid item xs={12} sm={4}>
        <Typography variant="h6" gutterBottom>회사 정보</Typography>
        <Typography>주소: 서울특별시 강남구 </Typography>
        <Typography>전화: 02-1234-5678</Typography>
        <Typography>Email: info@myblog.com</Typography>
      </Grid>
      <Grid item xs={12} sm={4}>
        <Typography variant="h6" gutterBottom>링크</Typography>
        <Link href="#" color="inherit">홈</Link><br />
        <Link href="#" color="inherit">블로그</Link><br />
        <Link href="#" color="inherit">포트폴리오</Link><br />
        <Link href="#" color="inherit">연락처</Link>
      </Grid>
      <Grid item xs={12} sm={4}>
        <Typography variant="h6" gutterBottom>소셜 미디어</Typography>
        <IconButton color="inherit">
          <Facebook />
        </IconButton>
        <IconButton color="inherit">
          <Instagram />
        </IconButton>
        <IconButton color="inherit">
          <Twitter />
        </IconButton>
        <IconButton color="inherit">
          <LinkedIn />
        </IconButton>
      </Grid>
    </Grid>
    <Box mt={3}>
      <Typography variant="body2">© 2025 나의 블로그. 모든 권리 보유.</Typography>
    </Box>
  </Container>
</footer>
    </div>
  );
}

export default BlogHome;
