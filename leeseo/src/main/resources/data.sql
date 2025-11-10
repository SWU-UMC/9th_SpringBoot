//지역
INSERT INTO location (id, name) VALUES (1, "안암동");

//가게
INSERT INTO store (id, name, location_id, detail_address, manager_number) VALUES(1, "반이학생마라탕마라반", 1, "안암동 안암로 12",12);
INSERT INTO store (id, name, location_id, detail_address, manager_number) VALUES(2, "간장공장공장장", 1, "안암동 안암로 14", 14);

//회원
INSERT INTO member (id, name, gender, birth, address, detail_address, social_uid, social_type, point, email, phone_number, created_at, updated_at)
VALUES (1, '관리자', 'MALE', '1990-01-01', '강남구', '강남구 역삼동', 'admin_social_uid', 'KAKAO', 1000, 'admin@umc.com', '010-1111-2222', NOW(), NOW());

INSERT INTO member (id, name, gender, birth, address, detail_address, social_uid, social_type, point, email, phone_number, created_at, updated_at)
VALUES (2, '김민지', 'FEMALE', '1998-05-15', '성북구', '성북구 안암동', 'minji_social_uid', 'NAVER', 500, 'minji@example.com', '010-3333-4444', NOW(), NOW());

INSERT INTO member (id, name, gender, birth, address, detail_address, social_uid, social_type, point, email, phone_number, created_at, updated_at)
VALUES (3, '관리자', 'MALE', '1990-01-01', '강남구', '강남구 역삼동', 'admin_social_uid', 'KAKAO', 1000, 'admin@umc.com', '010-1111-2222', NOW(), NOW());

INSERT INTO member (id, name, gender, birth, address, detail_address, social_uid, social_type, point, email, phone_number, created_at, updated_at)
VALUES (4, '김민식', 'MALE', '1998-05-15', '성북구', '성북구 안암동', 'minji_social_uid', 'NAVER', 500, 'minji@example.com', '010-3333-4444', NOW(), NOW());

//리뷰
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (1, 1, 1, '정말 맛있어요!', 4.5, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (2, 2, 1, '마라탕은 역시 반이학생마라탕마라반', 5.0, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (3, 3, 1, '제 입맛엔 별로였어요.', 3.0, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (4, 4, 2, '좀 짜긴 한데 맛있어용', 3.8, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (5, 1, 2, '간장 맛이 심해요', 3.4, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (6, 2, 2, '가게가 더러운데 맛은 있어요', 3.1, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (7, 3, 1, '서비스도 주시고 좋아요', 3.9, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (8, 4, 1, '꿔바로우가 맛있어요', 3.8, NOW(), NOW());
INSERT INTO review (id, member_id, store_id, content, rate, created_at, updated_at) VALUES (9, 1, 1, '좀 비쌌어요', 3.3, NOW(), NOW());

