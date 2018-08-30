-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 30 Sie 2018, 22:09
-- Wersja serwera: 10.1.16-MariaDB
-- Wersja PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `restaurant`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `drinks`
--

CREATE TABLE `drinks` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `drinks`
--

INSERT INTO `drinks` (`id`, `name`, `price`) VALUES
(1, 'Coca-Cola (250ml)', 7),
(2, 'Cocla-Cola Zero (250ml)', 7),
(3, 'Fanta (250ml)', 7),
(4, 'Sprite (250ml)', 7),
(5, 'Tonic Kinley (250ml)', 8),
(6, 'Sok pomarańczowy (330ml)', 12),
(7, 'Burn (250ml)', 9),
(8, 'Woda gazowana/niegazowana (500ml)', 5),
(9, 'Lemoniada (250ml)', 14),
(10, 'Sok jabłkowy (330ml)', 8);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `free_terms`
--

CREATE TABLE `free_terms` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `free_terms`
--

INSERT INTO `free_terms` (`id`, `date`) VALUES
(1, '2018-06-21 12:00:00'),
(1, '2018-06-25 13:00:00'),
(2, '2018-06-22 10:00:00'),
(2, '2018-06-22 10:00:00'),
(3, '2018-06-25 11:00:00'),
(3, '2018-06-26 12:00:00'),
(4, '2018-06-27 09:00:00'),
(4, '2018-06-27 08:00:00'),
(4, '2018-06-28 16:00:00'),
(5, '2018-06-21 08:00:00'),
(5, '2018-06-22 09:00:00'),
(5, '2018-06-29 11:00:00'),
(5, '2018-06-21 04:00:00'),
(6, '2018-06-30 17:00:00'),
(6, '2018-06-28 09:00:00'),
(6, '2018-06-22 10:00:00'),
(6, '2018-06-23 14:00:00'),
(2, '2018-06-28 20:00:00'),
(2, '2018-09-02 08:00:00'),
(1, '2018-09-02 10:00:00'),
(2, '2018-09-02 12:00:00'),
(3, '2018-08-30 11:00:00'),
(4, '2018-09-02 12:00:00'),
(4, '2018-09-02 20:00:00'),
(4, '2018-09-02 04:00:00'),
(5, '2018-09-02 09:00:00'),
(5, '2018-09-02 11:00:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `main_courses`
--

CREATE TABLE `main_courses` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `ingredients` varchar(255) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `main_courses`
--

INSERT INTO `main_courses` (`id`, `name`, `description`, `ingredients`, `price`) VALUES
(1, 'PIERŚ Z KACZKI', 'Pierś z kaczki z burakami, kaszą bulgur, białą czekoladą, kawą oraz jabłkiem', 'buraki,kasza bulgur,pietruszka,biała czekolada,kawa,rajskie jabłuszko', 39),
(2, 'STEK Z ANTRYKOTU', 'Stek z boczniakami królewskimi, puree truflowym z demi glace i zielonym pieprzem', 'boczniak królewski,puree truflowe,zielony pieprz,demi glace', 45),
(3, 'SCHAB Z DZIKA', 'Schab z wędzonym jabłkiem, burakami, oraz skorzonerą z dereniem', 'wędzone jabłko,buraki,skorzonera,dereń', 45),
(4, 'SZNYCEL WIEPRZOWY', 'Sznycel podawany na sadzonym jajku, z pieczonymi ziemniakami oraz kiszonymi ogórkami', 'sadzone jajo,pieczone ziemniaki,kiszone ogórki', 29),
(5, 'TAGLIATELLE Z KOZIM SEREM', 'Tagiatelle z czosnkiem oraz chilli, ze szpinakiem', 'czosnek,chilli,szalotka,szpinak', 27),
(6, 'SANDACZ', 'Sandacz z risotto ze szpinakiem na zielonym groszku z ogórkami kiszonymi', 'risotto,szpinak,zielony groszek,kiszone pomidorki', 42),
(7, 'KARAŚ', 'Ryba podana z pieczonymi warzywami, rzodkiewką i oliwą szczypiorkową', 'pieczona włoszczyzna,rzodkiewka,oliwa szczypiorkowa', 32),
(8, 'PIEROGI RUSKIE', '', '', 25),
(9, 'TAGIATELLE Z KOZIM SEREM', 'Tagiatelle podane z kozim serem, czosnkiem z chilli oraz szpinakiem i szalotką', 'czosnek,chilli,szalotka,szpinak', 27),
(10, 'RAVIOLI Z KACZKI', 'Pieczona kaczka, sos holenderski, żel malinowy, cytryna, masło szałwiowe,\r\nParmigiano-Reggiano DOP', 'sos holenderki,żel malinowy,cytryna,masło szałwiowe,Parmigiano-Reggiano DOP', 31);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user` varchar(255) NOT NULL,
  `user_order` varchar(1500) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `changes` varchar(1500) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL DEFAULT 'Brak',
  `sum` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`id`, `user`, `user_order`, `changes`, `sum`) VALUES
(1, '123', '{KREWETKI TIGER=58.0}', '', 58),
(2, '123', '{Cocla-Cola Zero (250ml)=7.0, CARPACCIO DI FILETTO DI MANZO=32.0, Fanta (250ml)=7.0, Sprite (250ml)=7.0, CARPACCIO Z BURAKÓW=17.0, BRUSCHETTA CON POMODORI=16.0, KREWETKI TIGER=29.0}', 'chce cole zero', 115),
(3, '123', 'PIER? Z KACZKI,SCHAB Z DZIKA,Zupa warzywna,Rosó? z kury,Zupa pomidorowa,STEK Z ANTRYKOTU,', '', 157.5),
(4, '123', 'PIER? Z KACZKI,SCHAB Z DZIKA,Zupa warzywna,Rosó? z kury,Zupa pomidorowa,STEK Z ANTRYKOTU,', '', 157.5),
(5, '123', 'CARPACCIO Z BURAKÓW,BRUSCHETTA CON POMODORI,KREWETKI TIGER,', '', 62),
(6, 'mgilewski', 'CARPACCIO DI FILETTO DI MANZO,CARPACCIO Z BURAKÓW,BRUSCHETTA CON POMODORI,KREWETKI TIGER,', '', 94),
(7, '123', 'CARPACCIO Z BURAKÓW,BRUSCHETTA CON POMODORI,KREWETKI TIGER,', '', 62),
(8, '123', 'KREWETKI TIGER,', '', 29),
(9, '123', 'PIER? Z KACZKI,SCHAB Z DZIKA,STEK Z ANTRYKOTU,', '', 129),
(10, '123', 'PIER? Z KACZKI,', '', 39),
(11, '123', 'PIER? Z KACZKI,STEK Z ANTRYKOTU,', '', 84),
(12, '123', 'CARPACCIO Z BURAKÓW,BRUSCHETTA CON POMODORI,KREWETKI TIGER,', '', 62),
(13, '123', 'CARPACCIO Z BURAKÓW,KREWETKI TIGER,', '', 46),
(14, '123', 'KREWETKI TIGER,', '', 29);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reserved_tables`
--

CREATE TABLE `reserved_tables` (
  `id` int(11) NOT NULL,
  `table_number` int(2) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `number` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `reserved_tables`
--

INSERT INTO `reserved_tables` (`id`, `table_number`, `date`, `first_name`, `last_name`, `email`, `number`) VALUES
(5, 1, '2018-09-02 07:00:00', 'Maksym', 'Gilewski', 'maksym.gilewski@gmail.com', 501418527);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `snacks`
--

CREATE TABLE `snacks` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `snacks`
--

INSERT INTO `snacks` (`id`, `name`, `description`, `price`) VALUES
(1, 'KREWETKI TIGER', 'Krewetki podawane z czosnkiem, chilli, szalotką i białym winem z parmezanem, kolendrą i bagietka', 29),
(2, 'CARPACCIO Z BURAKÓW', 'Podawane z chałwą, kozim serem, rucolą oraz sezamem', 17),
(3, 'BRUSCHETTA CON POMODORI', 'Grzanki ze świeżymi pomidorami, czosnkiem, bazylią, rukolą i Grana Padano', 16),
(4, 'CARPACCIO DI FILETTO DI MANZO', 'Carpaccio z marynowanej polędwicy wołowej, rukola, Grana Padano, czarne oliwki', 32),
(5, 'FOCACCIA ALL''AGLIO', 'Podpłomyk z czosnkiem', 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `soups`
--

CREATE TABLE `soups` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `soups`
--

INSERT INTO `soups` (`id`, `name`, `price`) VALUES
(1, 'Rosół z kury', 8),
(2, 'Zupa pomidorowa', 9.5),
(3, 'Zupa warzywna', 11),
(4, 'Zupa cebulowa z serem', 9),
(5, 'Zupa grzybowa', 10.5),
(6, 'Zupa krem z brokuł', 13),
(7, 'Zupa krem z dynii', 15),
(8, 'Barszcz czerwony z krokietem', 14.5),
(9, 'Barszcz biały', 8),
(10, 'Zupa krem z kalafiora', 11.5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(500) NOT NULL,
  `bonus` int(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `address`, `bonus`) VALUES
(1, 'mgilewski', '123', 'mgilewski@gmail.com', 'ul. Warszawska 24, 00-000 Kraków', 0),
(4, '123', '123', '@', '123', 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `drinks`
--
ALTER TABLE `drinks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `main_courses`
--
ALTER TABLE `main_courses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reserved_tables`
--
ALTER TABLE `reserved_tables`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `snacks`
--
ALTER TABLE `snacks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `soups`
--
ALTER TABLE `soups`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `drinks`
--
ALTER TABLE `drinks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `main_courses`
--
ALTER TABLE `main_courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT dla tabeli `reserved_tables`
--
ALTER TABLE `reserved_tables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `snacks`
--
ALTER TABLE `snacks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `soups`
--
ALTER TABLE `soups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
