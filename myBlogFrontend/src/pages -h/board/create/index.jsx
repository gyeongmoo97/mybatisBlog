// pages/board/create/index.jsx
import React from 'react';
import PostForm from '../../components/PostForm';

const CreatePost = () => {

  return (
    <div>
      <PostForm mode="create" initialData={{ title: '', content: '' }} />
    </div>
  );
};

export default CreatePost;
