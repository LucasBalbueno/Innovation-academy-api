INSERT IGNORE INTO users (
  password, first_name, last_name, email, number_phone,
  job, technologies, biography, day_count, username, user_image
) VALUES (
  'senhaSegura123', 'John', 'Doe', 'johndoe@example.com', '+123456789',
  'Developer', 'Java, Spring, React', 'Experienced developer with a passion for innovation.',
  365, 'johndoe', 'https://example.com/images/johndoe.jpg'
);