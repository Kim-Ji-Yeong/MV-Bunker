CREATE TABLE t_user (
        i_user INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        uid VARCHAR(20) UNIQUE,
        ex_key VARCHAR(50),
        provider VARCHAR(15) NOT NULL DEFAULT 'local',
        upw VARCHAR(100),
        unm VARCHAR(10) NOT NULL,
        age INT(3),
        unn VARCHAR(20),
        profileImg VARCHAR(40),
        regdt DATETIME DEFAULT NOW(),
        auth VARCHAR(10),
        introduce VARCHAR(500),
        UNIQUE KEY UK_social (ex_key,provider)
);

CREATE TABLE t_review (
    i_review INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    id INT UNSIGNED NOT NULL,
    m_title VARCHAR(100) not null,
    poster varchar(100),
    i_user INT UNSIGNED,
    re_ctnt VARCHAR(500) NOT NULL,
    regdt DATETIME DEFAULT NOW(),
    FOREIGN KEY (i_user) REFERENCES t_user (i_user) ON DELETE CASCADE,
    UNIQUE key chk_revw(id, i_user)
);

CREATE TABLE t_review_like (
    i_review INT UNSIGNED,
    i_user INT UNSIGNED,
    disLike int default 0 check (disLike in(0,1)),
    PRIMARY KEY (i_review, i_user),
    FOREIGN KEY (i_review) REFERENCES t_review (i_review) ON DELETE CASCADE,
    FOREIGN KEY (i_user) REFERENCES t_user (i_user) ON DELETE CASCADE
);

CREATE TABLE t_review_cmt (
    i_cmt INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    i_user INT UNSIGNED,
    i_review INT UNSIGNED,
    cmt VARCHAR(100) NOT NULL,
    regdt DATETIME DEFAULT NOW(),
    FOREIGN KEY (i_user) REFERENCES t_user (i_user) ON DELETE CASCADE,
    FOREIGN KEY (i_review) REFERENCES t_review (i_review) ON DELETE CASCADE
);

CREATE TABLE t_review_cmt_like (
    i_cmt INT UNSIGNED,
    i_user INT UNSIGNED,
    disLike INT DEFAULT 0 CHECK (disLike IN (0, 1)),
    PRIMARY KEY (i_cmt, i_user)
);

#
CREATE TABLE t_movie_fav (
    id INT UNSIGNED,
    i_user INT UNSIGNED,
    m_title VARCHAR(100) not null,
    poster varchar(100),
    PRIMARY KEY (id, i_user),
    FOREIGN KEY (i_user) REFERENCES t_user (i_user) ON DELETE CASCADE
);

CREATE TABLE t_eval (
    i_user INT UNSIGNED,
    id INT UNSIGNED NOT NULL,
    production float UNSIGNED NOT NULL CHECK (production <= 5),
    performance float UNSIGNED NOT NULL CHECK (performance <= 5),
    visual_beauty float UNSIGNED NOT NULL CHECK (visual_beauty <= 5),
    music float UNSIGNED NOT NULL CHECK (music <= 5),
    plot float UNSIGNED NOT NULL CHECK (plot <= 5),
    PRIMARY KEY (i_user, id),
    FOREIGN KEY (i_user) REFERENCES t_user (i_user)  ON DELETE CASCADE,
    foreign key (id) references t_review(id)
);

CREATE TABLE t_sub (
    sub_ing_user INT UNSIGNED NOT NULL,
    sub_ed_user INT UNSIGNED NOT NULL,
    PRIMARY KEY (sub_ing_user, sub_ed_user),
    FOREIGN KEY (sub_ing_user) REFERENCES t_user (i_user) ON DELETE CASCADE,
    FOREIGN KEY (sub_ed_user) REFERENCES t_user (i_user) ON DELETE CASCADE
);

create table t_qna(
    i_user int unsigned auto_increment,
    i_que int unsigned auto_increment primary key,
    que_title varchar(50) not null,
    que_ctnt varchar(500) not null,
    foreign key (i_user) references t_user(i_user) on delete CASCADE
);