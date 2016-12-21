--
-- Database: `questionit_acc`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer_list`
--

CREATE TABLE `answer_list` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer_list`
--

INSERT INTO `answer_list` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11);

-- --------------------------------------------------------

--
-- Table structure for table `answer_list_answers`
--

CREATE TABLE `answer_list_answers` (
  `answer_list_id` bigint(20) NOT NULL,
  `answers` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer_list_answers`
--

INSERT INTO `answer_list_answers` (`answer_list_id`, `answers`) VALUES
(2, b'0'),
(2, b'1'),
(3, b'1'),
(2, b'0'),
(2, b'0'),
(2, b'0'),
(2, b'0'),
(2, b'0'),
(2, b'0'),
(2, b'0'),
(3, b'1'),
(3, b'0'),
(3, b'0'),
(3, b'0'),
(3, b'0'),
(3, b'0'),
(3, b'0'),
(3, b'0'),
(3, b'0'),
(3, b'0'),
(4, b'0'),
(4, b'1'),
(4, b'0'),
(4, b'0'),
(4, b'0'),
(4, b'0'),
(4, b'0'),
(4, b'0'),
(4, b'0'),
(4, b'0'),
(5, b'1'),
(5, b'0'),
(5, b'0'),
(5, b'1'),
(5, b'0'),
(5, b'0'),
(5, b'0'),
(5, b'0'),
(5, b'0'),
(5, b'0'),
(6, b'1'),
(6, b'1'),
(6, b'0'),
(6, b'0'),
(6, b'0'),
(6, b'0'),
(6, b'0'),
(6, b'0'),
(6, b'0'),
(6, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(7, b'0'),
(8, b'0'),
(8, b'0'),
(8, b'1'),
(8, b'0'),
(8, b'0'),
(8, b'0'),
(8, b'0'),
(8, b'0'),
(8, b'0'),
(8, b'0'),
(9, b'1'),
(9, b'1'),
(9, b'0'),
(9, b'0'),
(9, b'0'),
(9, b'0'),
(9, b'0'),
(9, b'0'),
(9, b'0'),
(9, b'0'),
(1, b'0'),
(1, b'1'),
(1, b'0'),
(1, b'0'),
(1, b'0'),
(1, b'0'),
(1, b'0'),
(1, b'0'),
(1, b'0'),
(1, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(10, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0'),
(11, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `attempt`
--

CREATE TABLE `attempt` (
  `id` bigint(20) NOT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `start_date_time` datetime DEFAULT NULL,
  `time_to_complete_in_seconds` int(11) NOT NULL,
  `test_template_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attempt`
--

INSERT INTO `attempt` (`id`, `end_date_time`, `start_date_time`, `time_to_complete_in_seconds`, `test_template_id`) VALUES
(1, NULL, '2016-12-12 11:10:45', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `attempt_given_answers`
--

CREATE TABLE `attempt_given_answers` (
  `attempt_id` bigint(20) NOT NULL,
  `given_answers_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attempt_given_answers`
--

INSERT INTO `attempt_given_answers` (`attempt_id`, `given_answers_id`) VALUES
(1, 10),
(1, 11);

-- --------------------------------------------------------

--
-- Table structure for table `attempt_marked_questions`
--

CREATE TABLE `attempt_marked_questions` (
  `attempt_id` bigint(20) NOT NULL,
  `marked_questions` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `start_date_time` datetime DEFAULT NULL,
  `time_to_complete_in_seconds` int(11) NOT NULL,
  `question_list_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`id`, `end_date_time`, `start_date_time`, `time_to_complete_in_seconds`, `question_list_id`) VALUES
(1, '2016-11-15 11:15:32', '2016-11-15 09:15:32', 7200, 1),
(2, '2016-11-15 11:05:32', '2016-11-15 09:15:32', 6600, 1),
(3, '2016-11-15 11:25:32', '2016-11-15 09:15:32', 7800, 2);

-- --------------------------------------------------------

--
-- Table structure for table `exam_given_answers`
--

CREATE TABLE `exam_given_answers` (
  `exam_id` bigint(20) NOT NULL,
  `given_answers_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam_given_answers`
--

INSERT INTO `exam_given_answers` (`exam_id`, `given_answers_id`) VALUES
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(3, 6),
(3, 7),
(3, 9);

-- --------------------------------------------------------

--
-- Table structure for table `exam_marked_questions`
--

CREATE TABLE `exam_marked_questions` (
  `exam_id` bigint(20) NOT NULL,
  `marked_questions` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam_marked_questions`
--

INSERT INTO `exam_marked_questions` (`exam_id`, `marked_questions`) VALUES
(1, 0),
(2, 0),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_valid` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`id`, `email`, `first_name`, `is_valid`, `last_name`, `password`) VALUES
(1, 'rosalynn.hardy@gmail.com', 'Rosalynn', b'1', 'Hardy', 'test987'),
(2, 'digitaleservice@gmail.com', 'Felix', b'1', 'van Loenen', 'test876');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL,
  `creation_date_time` datetime DEFAULT NULL,
  `for_exam` int(11) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `programming_language` int(11) DEFAULT NULL,
  `explantion_answer` varchar(255) DEFAULT NULL,
  `is_obsolete` bit(1) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `type_of_question` varchar(255) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `correct_answers_id` bigint(20) DEFAULT NULL,
  `explanation_answer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `creation_date_time`, `for_exam`, `is_enabled`, `name`, `programming_language`, `explantion_answer`, `is_obsolete`, `question`, `type_of_question`, `creator_id`, `correct_answers_id`, `explanation_answer`) VALUES
(1, '2016-11-15 09:15:32', 1, b'1', 'Testing for loop', 1, 'Yes, the for loop is correct!', b'0', 'Is the following statement correct?\r\n<pre>\r\n  for (int a = 0; a < 10; a++)\r\n    a = a * a;\r\n  }\r\n</pre>\r\nOr do you prefer?\r\n<pre>\r\n  for (int a = 0; a < 10; a++)\r\n    b = a + a;\r\n  }\r\n</pre>', 'for loop', 1, 1, NULL),
(2, '2016-11-15 09:17:32', 3, b'1', 'If in for', 2, 'Yes, correct!', b'0', 'Is the following statement correct?\r\n<pre>\r\n  for (int a = 0; a < 10; a++)\r\n    if (a * a > 10) {\r\n	    break;\r\n	  }\r\n  }\r\n</pre>', 'if', 1, 6, NULL),
(3, '2016-12-09 11:07:32', 3, b'1', 'A random topic', 2, 'Some explanation', b'0', 'Is the following statement correct?\r\n<pre>\r\n  A a = new A();\r\n</pre>', 'Object declaration', 1, 1, NULL),
(4, '2016-12-09 11:07:32', 3, b'1', 'scope', 2, 'The scope of a variable determines where and how long a variable lives.', b'1', 'After which line number does the scope of variable x end?\r\n<pre>\r\n1  private int calcY(int a) {\r\n2    int y = 0;\r\n3    for (int x = 0; x < 5; x++) {\r\n4      y = ++y + x;\r\n5    }\r\n6    System.out.println(y);\r\n7  }\r\n</pre>', 'Object declaration', 1, 1, NULL),
(5, '2016-12-12 10:52:54', 3, b'1', 'scope', 2, NULL, b'1', 'After which line number does the scope of variable x end?\r\n<pre>\r\n1  private int calcY(int a) {\r\n2    int y = 0;\r\n3    for (int x = 0; x < 5; x++) {\r\n4      y = ++y + x;\r\n5    }\r\n6    System.out.println(y);\r\n7  }\r\n</pre>', 'Object declaration', 1, 1, NULL),
(6, '2016-12-12 10:53:44', 1, b'1', 'What is the scope of x?', 1, NULL, b'1', 'After which line number does the scope of variable x end?\r\n<pre>\r\n1  private int calcY(int a) {\r\n2    int y = 0;\r\n3    for (int x = 0; x < 5; x++) {\r\n4      y = ++y + x;\r\n5    }\r\n6    System.out.println(y);\r\n7  }\r\n</pre>', 'scope', 1, 1, NULL),
(7, '2016-12-12 11:01:10', 1, b'1', 'What is the scope of x?', 1, NULL, b'0', 'After which line number does the scope of variable x end?\n<pre>\n1  private int calcY(int a) {\n2    int y = 0;\n3    for (int x = 0; x < 5; x++) {\n4      y = ++y + x;\n5    }\n6    return y;\n7  }\n</pre>', 'scope', 1, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `question_given_answers`
--

CREATE TABLE `question_given_answers` (
  `question_id` bigint(20) NOT NULL,
  `given_answers_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_given_answers`
--

INSERT INTO `question_given_answers` (`question_id`, `given_answers_id`) VALUES
(1, 3),
(1, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 9);

-- --------------------------------------------------------

--
-- Table structure for table `question_list`
--

CREATE TABLE `question_list` (
  `id` bigint(20) NOT NULL,
  `creation_date_time` datetime DEFAULT NULL,
  `for_exam` int(11) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `programming_language` int(11) DEFAULT NULL,
  `exam_time_in_minutes` int(11) NOT NULL,
  `creator_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_list`
--

INSERT INTO `question_list` (`id`, `creation_date_time`, `for_exam`, `is_enabled`, `name`, `programming_language`, `exam_time_in_minutes`, `creator_id`) VALUES
(1, '2016-11-15 09:25:32', 1, b'1', 'First OCA exam', 1, 120, 1),
(2, '2016-11-15 09:35:32', 1, b'1', 'Second OCA exam', 1, 120, 2);

-- --------------------------------------------------------

--
-- Table structure for table `question_list_questions`
--

CREATE TABLE `question_list_questions` (
  `question_list_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_list_questions`
--

INSERT INTO `question_list_questions` (`question_list_id`, `questions_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `question_possible_answers`
--

CREATE TABLE `question_possible_answers` (
  `question_id` bigint(20) NOT NULL,
  `possible_answers` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_possible_answers`
--

INSERT INTO `question_possible_answers` (`question_id`, `possible_answers`) VALUES
(1, 'Yes, it is correct'),
(1, 'No, a*a cannot be within a for loop'),
(2, 'Yes, it is correct'),
(2, 'No, a*a cannot be within a for loop'),
(2, 'No, the break is not allowed in the if statement'),
(3, 'Yes'),
(3, 'No'),
(4, 'Line 3'),
(4, 'Line 5'),
(4, 'Line 7'),
(5, 'Line 3'),
(5, 'Line 5'),
(5, 'Line 7'),
(6, 'Line 3'),
(6, 'Line 5'),
(6, 'Line 7'),
(7, 'Line 3'),
(7, 'Line 5'),
(7, 'Line 7');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_valid` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `email`, `first_name`, `is_valid`, `last_name`, `password`) VALUES
(1, 'rvandemaat@hotmail.com', 'Rik', b'1', 'van de Maat', 'test123'),
(3, 'remond.karst@gmail.com', 'Remond', b'1', 'Karst', 'test345');

-- --------------------------------------------------------

--
-- Table structure for table `student_attempts`
--

CREATE TABLE `student_attempts` (
  `student_id` bigint(20) NOT NULL,
  `attempts_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_attempts`
--

INSERT INTO `student_attempts` (`student_id`, `attempts_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `student_class`
--

CREATE TABLE `student_class` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_class`
--

INSERT INTO `student_class` (`id`, `name`) VALUES
(1, 'JAVA 1 - Twente'),
(2, 'JAVA 2 - Eindhoven'),
(3, 'JAVA 1 - Twente');

-- --------------------------------------------------------

--
-- Table structure for table `student_class_instructors`
--

CREATE TABLE `student_class_instructors` (
  `student_classes_id` bigint(20) NOT NULL,
  `instructors_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_class_students`
--

CREATE TABLE `student_class_students` (
  `student_class_id` bigint(20) NOT NULL,
  `students_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_class_students`
--

INSERT INTO `student_class_students` (`student_class_id`, `students_id`) VALUES
(1, 1),
(2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `student_exams`
--

CREATE TABLE `student_exams` (
  `student_id` bigint(20) NOT NULL,
  `exams_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `test_template`
--

CREATE TABLE `test_template` (
  `id` bigint(20) NOT NULL,
  `creation_date_time` datetime DEFAULT NULL,
  `for_exam` int(11) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `programming_language` int(11) DEFAULT NULL,
  `attempt_time_in_minutes` int(11) NOT NULL,
  `creator_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_template`
--

INSERT INTO `test_template` (`id`, `creation_date_time`, `for_exam`, `is_enabled`, `name`, `programming_language`, `attempt_time_in_minutes`, `creator_id`) VALUES
(1, '2016-12-12 10:19:00', 1, b'1', 'Test template 1', 1, 120, 1),
(2, '2016-12-12 10:21:00', 1, b'1', 'Test template 2', 1, 150, 1);

-- --------------------------------------------------------

--
-- Table structure for table `test_template_questions`
--

CREATE TABLE `test_template_questions` (
  `test_template_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_template_questions`
--

INSERT INTO `test_template_questions` (`test_template_id`, `questions_id`) VALUES
(1, 1),
(1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer_list`
--
ALTER TABLE `answer_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `answer_list_answers`
--
ALTER TABLE `answer_list_answers`
  ADD KEY `FKbje97nhb34tsf4lvf63p09vkr` (`answer_list_id`);

--
-- Indexes for table `attempt`
--
ALTER TABLE `attempt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKidwbvmraeltukb9p2m22tqppm` (`test_template_id`);

--
-- Indexes for table `attempt_given_answers`
--
ALTER TABLE `attempt_given_answers`
  ADD UNIQUE KEY `UK_1h6oer4dtn43dgso9rneq0bhj` (`given_answers_id`),
  ADD KEY `FKf6k0uo5oxi5y08ohfl8d246jv` (`attempt_id`);

--
-- Indexes for table `attempt_marked_questions`
--
ALTER TABLE `attempt_marked_questions`
  ADD KEY `FKi9ku74j9vd4emyi76pmnay6o3` (`attempt_id`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKds7ok7bh4uylcx2m2oco7rd1e` (`question_list_id`);

--
-- Indexes for table `exam_given_answers`
--
ALTER TABLE `exam_given_answers`
  ADD UNIQUE KEY `UK_g6h0r6n6gn5m1wf0gtavfegut` (`given_answers_id`),
  ADD KEY `FK1ec3n3p9mlgqnk3a133b3ketg` (`exam_id`);

--
-- Indexes for table `exam_marked_questions`
--
ALTER TABLE `exam_marked_questions`
  ADD KEY `FK6wk9lyxkqxnqjve31mo7k82o` (`exam_id`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeskhoid39h4kaq1ci6sr69da2` (`creator_id`),
  ADD KEY `FKl67ub912a6lbin7hbjdftk2x5` (`correct_answers_id`);

--
-- Indexes for table `question_given_answers`
--
ALTER TABLE `question_given_answers`
  ADD UNIQUE KEY `UK_a8cygsduewwmne9w9w8eu0ugm` (`given_answers_id`),
  ADD KEY `FKtktl6smrd3et8ne1coaun8a4e` (`question_id`);

--
-- Indexes for table `question_list`
--
ALTER TABLE `question_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdqlpbux8rlkh9pchx0f5bbmsn` (`creator_id`);

--
-- Indexes for table `question_list_questions`
--
ALTER TABLE `question_list_questions`
  ADD KEY `FK1a2jkhww5panvbd8bl29m3ydr` (`questions_id`),
  ADD KEY `FK2kahyvnx0i6bl74c9gj5meyaj` (`question_list_id`);

--
-- Indexes for table `question_possible_answers`
--
ALTER TABLE `question_possible_answers`
  ADD KEY `FK544lw2hqd3dw3uvfkt14cg7a7` (`question_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_attempts`
--
ALTER TABLE `student_attempts`
  ADD UNIQUE KEY `UK_qgrmppe5stag4hhqhh3yvh7qh` (`attempts_id`),
  ADD KEY `FKkcdwwd62ppnxcgauo3olgg1is` (`student_id`);

--
-- Indexes for table `student_class`
--
ALTER TABLE `student_class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_class_instructors`
--
ALTER TABLE `student_class_instructors`
  ADD KEY `FKl2wvyf3lauhnp1kx7qicqm4s6` (`instructors_id`),
  ADD KEY `FK63dmb42sui4pqh77bt86m0lmn` (`student_classes_id`);

--
-- Indexes for table `student_class_students`
--
ALTER TABLE `student_class_students`
  ADD UNIQUE KEY `UK_98gli8ogeso9rwaks60ttcnyd` (`students_id`),
  ADD KEY `FK3u4poub6ja7cowccjc68w0ng0` (`student_class_id`);

--
-- Indexes for table `student_exams`
--
ALTER TABLE `student_exams`
  ADD UNIQUE KEY `UK_6kp5cqgtfr9i754gl4dvihscb` (`exams_id`),
  ADD KEY `FKpeeamugwxrlq82rnroahwesod` (`student_id`);

--
-- Indexes for table `test_template`
--
ALTER TABLE `test_template`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK63kkx549sht7onb3mpnw1rntu` (`creator_id`);

--
-- Indexes for table `test_template_questions`
--
ALTER TABLE `test_template_questions`
  ADD KEY `FKpg1xatmcwbr7udcipvf4vtpha` (`questions_id`),
  ADD KEY `FK6xphhq3oolmb9c2j2q279k7p5` (`test_template_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer_list`
--
ALTER TABLE `answer_list`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `attempt`
--
ALTER TABLE `attempt`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `instructor`
--
ALTER TABLE `instructor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `question_list`
--
ALTER TABLE `question_list`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `student_class`
--
ALTER TABLE `student_class`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `test_template`
--
ALTER TABLE `test_template`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer_list_answers`
--
ALTER TABLE `answer_list_answers`
  ADD CONSTRAINT `FKbje97nhb34tsf4lvf63p09vkr` FOREIGN KEY (`answer_list_id`) REFERENCES `answer_list` (`id`);

--
-- Constraints for table `attempt`
--
ALTER TABLE `attempt`
  ADD CONSTRAINT `FKidwbvmraeltukb9p2m22tqppm` FOREIGN KEY (`test_template_id`) REFERENCES `test_template` (`id`);

--
-- Constraints for table `attempt_given_answers`
--
ALTER TABLE `attempt_given_answers`
  ADD CONSTRAINT `FKf6k0uo5oxi5y08ohfl8d246jv` FOREIGN KEY (`attempt_id`) REFERENCES `attempt` (`id`),
  ADD CONSTRAINT `FKstgquy908oy7n4po5to9uh6rw` FOREIGN KEY (`given_answers_id`) REFERENCES `answer_list` (`id`);

--
-- Constraints for table `attempt_marked_questions`
--
ALTER TABLE `attempt_marked_questions`
  ADD CONSTRAINT `FKi9ku74j9vd4emyi76pmnay6o3` FOREIGN KEY (`attempt_id`) REFERENCES `attempt` (`id`);

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `FKds7ok7bh4uylcx2m2oco7rd1e` FOREIGN KEY (`question_list_id`) REFERENCES `question_list` (`id`);

--
-- Constraints for table `exam_given_answers`
--
ALTER TABLE `exam_given_answers`
  ADD CONSTRAINT `FK1ec3n3p9mlgqnk3a133b3ketg` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `FKrsyp12msxqgo1kuus3jk5kk7y` FOREIGN KEY (`given_answers_id`) REFERENCES `answer_list` (`id`);

--
-- Constraints for table `exam_marked_questions`
--
ALTER TABLE `exam_marked_questions`
  ADD CONSTRAINT `FK6wk9lyxkqxnqjve31mo7k82o` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `FKeskhoid39h4kaq1ci6sr69da2` FOREIGN KEY (`creator_id`) REFERENCES `instructor` (`id`),
  ADD CONSTRAINT `FKl67ub912a6lbin7hbjdftk2x5` FOREIGN KEY (`correct_answers_id`) REFERENCES `answer_list` (`id`);

--
-- Constraints for table `question_given_answers`
--
ALTER TABLE `question_given_answers`
  ADD CONSTRAINT `FKhvtfjj4vbmb5k6glu5sxi83vf` FOREIGN KEY (`given_answers_id`) REFERENCES `answer_list` (`id`),
  ADD CONSTRAINT `FKtktl6smrd3et8ne1coaun8a4e` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

--
-- Constraints for table `question_list`
--
ALTER TABLE `question_list`
  ADD CONSTRAINT `FKdqlpbux8rlkh9pchx0f5bbmsn` FOREIGN KEY (`creator_id`) REFERENCES `instructor` (`id`);

--
-- Constraints for table `question_list_questions`
--
ALTER TABLE `question_list_questions`
  ADD CONSTRAINT `FK1a2jkhww5panvbd8bl29m3ydr` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`),
  ADD CONSTRAINT `FK2kahyvnx0i6bl74c9gj5meyaj` FOREIGN KEY (`question_list_id`) REFERENCES `question_list` (`id`);

--
-- Constraints for table `question_possible_answers`
--
ALTER TABLE `question_possible_answers`
  ADD CONSTRAINT `FK544lw2hqd3dw3uvfkt14cg7a7` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

--
-- Constraints for table `student_attempts`
--
ALTER TABLE `student_attempts`
  ADD CONSTRAINT `FKkcdwwd62ppnxcgauo3olgg1is` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `FKs40ph8eo3d6uaahovlp5wp2uf` FOREIGN KEY (`attempts_id`) REFERENCES `attempt` (`id`);

--
-- Constraints for table `student_class_instructors`
--
ALTER TABLE `student_class_instructors`
  ADD CONSTRAINT `FK63dmb42sui4pqh77bt86m0lmn` FOREIGN KEY (`student_classes_id`) REFERENCES `student_class` (`id`),
  ADD CONSTRAINT `FKl2wvyf3lauhnp1kx7qicqm4s6` FOREIGN KEY (`instructors_id`) REFERENCES `instructor` (`id`);

--
-- Constraints for table `student_class_students`
--
ALTER TABLE `student_class_students`
  ADD CONSTRAINT `FK3u4poub6ja7cowccjc68w0ng0` FOREIGN KEY (`student_class_id`) REFERENCES `student_class` (`id`),
  ADD CONSTRAINT `FKn9v4c3flfslm6nxipvw2r6ngf` FOREIGN KEY (`students_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `student_exams`
--
ALTER TABLE `student_exams`
  ADD CONSTRAINT `FKewo50s6rlr9lycgev8xdx9h5x` FOREIGN KEY (`exams_id`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `FKpeeamugwxrlq82rnroahwesod` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `test_template`
--
ALTER TABLE `test_template`
  ADD CONSTRAINT `FK63kkx549sht7onb3mpnw1rntu` FOREIGN KEY (`creator_id`) REFERENCES `instructor` (`id`);

--
-- Constraints for table `test_template_questions`
--
ALTER TABLE `test_template_questions`
  ADD CONSTRAINT `FK6xphhq3oolmb9c2j2q279k7p5` FOREIGN KEY (`test_template_id`) REFERENCES `test_template` (`id`),
  ADD CONSTRAINT `FKpg1xatmcwbr7udcipvf4vtpha` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`);
