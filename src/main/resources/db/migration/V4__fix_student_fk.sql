ALTER TABLE loans DROP CONSTRAINT fk_loans_student;

ALTER TABLE loans ADD CONSTRAINT fk_loans_student
FOREIGN KEY (student_id)
REFERENCES students(id);