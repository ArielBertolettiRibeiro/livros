ALTER TABLE students
ADD COLUMN user_id INTEGER;

ALTER TABLE students
ADD CONSTRAINT fk_students_user
FOREIGN KEY (user_id)
REFERENCES users(id);