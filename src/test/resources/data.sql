set foreign_key_checks = 0;

INSERT INTO `detailSubjects` VALUES
('물리1','과탐'),
('물리2','과탐'),
('생물1','과탐'),
('생물2','과탐'),
('지구과학1','과탐'),
('지구과학2','과탐'),
('통합과학','과탐'),
('화학1','과탐'),
('화학2','과탐'),
('국어','국어'),
('통합사회','사탐'),
('수학','수학'),
('영어','영어');
INSERT INTO `exam` VALUES
(1,'2022 3월모의고사','2022-03-24','48,46,40,36,30,32,30,28','물리1',3),
(2,'2022 6월모의고사','2022-06-12','48,42,40,36,30,32,30,28','물리1',3);
INSERT INTO `record` VALUES
(1,1,'물리1',1,43),
(2,1,'물리1',2,43);
INSERT INTO `student` VALUES
(1,'홍길동',1,3,'물리1','그레잇고교',2022);
INSERT INTO `subjects` VALUES
('과탐'),
('국어'),
('사탐'),
('수학'),
('영어');
INSERT INTO `teacher` VALUES
(1,'test@test.com','과탐','테스트','2bbe7e30ee79bfed40109dbce86945cc6ca636125c11553e7b705b90033cf2bf');
set foreign_key_checks = 1;