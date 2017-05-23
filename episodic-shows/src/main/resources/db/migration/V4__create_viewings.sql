create table viewings (
  id bigint not null auto_increment primary key,
  user_id bigint not null,
  show_id bigint not null,
  episode_id bigint not null,
  updated_at datetime not null,
  timecode int not null,
  constraint fk_user_id foreign key (user_id) references users(id) on delete cascade,
  constraint fk_viewings_show_id foreign key (show_id) references shows(id) on delete cascade,
  constraint fk_episode_id foreign key (episode_id) references episodes(id) on delete cascade
);