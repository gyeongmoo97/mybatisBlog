-- UserProfile 테이블
CREATE TABLE IF NOT EXISTS UserProfile (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bio VARCHAR(500),
    profile_pic VARCHAR(255)
);

-- Posts 테이블
CREATE TABLE IF NOT EXISTS Posts (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    status VARCHAR(20) DEFAULT 'draft',
    view_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Comments 테이블
CREATE TABLE IF NOT EXISTS Comments (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id INT NOT NULL,
    parent_comment_id BIGINT,
    comment TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES Posts(post_id) ON DELETE CASCADE,
    FOREIGN KEY (parent_comment_id) REFERENCES Comments(comment_id) ON DELETE CASCADE
);

-- Categories 테이블
CREATE TABLE IF NOT EXISTS Categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- PostCategories 테이블 (게시물과 카테고리의 다대다 관계)
CREATE TABLE IF NOT EXISTS PostCategories (
    post_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (post_id, category_id),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE CASCADE
);

-- 기본 카테고리 데이터 삽입
INSERT INTO Categories (name) VALUES ('임시 저장');
INSERT INTO Categories (name) VALUES ('공지사항');
INSERT INTO Categories (name) VALUES ('일반');
