const INACTIVE = -1;
const SELECT_ALL_FLAG = 0;
const SELECT_REVERSE_FLAG = 1;
const SELECT_CANCEL_FLAG = 2;
const SELECT_UPLOAD_FLAG = 3;
function handle_active(val, o, e) {
  switch (val) {
    case SELECT_ALL_FLAG: {
      o.is_select = true;
      break;
    }
    case SELECT_REVERSE_FLAG: {
      o.is_select = !o.is_select;
      break;
    }
    case SELECT_CANCEL_FLAG: {
      o.is_select = false;
      break;
    }
    case SELECT_UPLOAD_FLAG: {
      if (o.is_select) {
        o.$store.commit('upload_data', e);
      }
      break;
    }
  }

}
Vue.use(Vuex);
const store = new Vuex.Store({
  state: {
    select_mode: INACTIVE,
    select_list: [],
    select_flags: INACTIVE,
    current_panel: 5
  },
  mutations: {
    switch_mode(state, payload) {
      state.select_mode = payload;
    },
    upload_data(state, payload) {
      state.select_list.push(payload);
    },
    empty_list(state) {
      state.select_list = [];
    },
    set_flag(state, payload) {
      state.select_flags = payload;
    },
    reset_flag(state) {
      state.select_flags = INACTIVE;
    },
    set_panel(state, payload) {
      state.current_panel = payload;
    },
    //网络请求生效时触发?
    reset_select(state) {
      state.select_mode = INACTIVE;
      state.select_list = [];
      state.select_flags = INACTIVE;
    }
  }
})
var domain = '';
const all_classes = [[{
  class_id: 1,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理'
}, {
  class_id: 2,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理'
}, {
  class_id: 3,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理'
}], [{
  class_id: 4,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理'
}, {
  class_id: 5,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理'
}, {
  class_id: 6,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理'
}], [{
  class_id: 7,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理'
}]];
const edit_classes = [[{
  class_id: 1,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理',
  is_examine: true
}, {
  class_id: 2,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理',
  is_examine: false
}, {
  class_id: 3,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理',
  is_examine: false
}], [{
  class_id: 4,
  class_cover: 'static/image/classes/1.jpg',
  class_name: '操作系统原理',
  is_examine: false
}]];
const left_list_data = [{
  name: '课程',
  icon: 'fa fa-book',
  style: ''
}, {
  name: '学习计划',
  icon: 'fa fa-list-ol',
  style: ''
}, {
  name: '消息',
  icon: 'fa fa-envelope',
  style: ''
}, {
  name: '个人设置',
  icon: 'fa fa-cog fa-fw',
  style: 'left: 5px'
}]
const all_message = [{
  message_id: 1,
  message_detail: '真的有那么Q弹吗',
  sender_id: 1,
  is_read: false
}, {
  message_id: 2,
  message_detail: '真的有那么丝滑吗',
  sender_id: 3,
  is_read: false
}, {
  message_id: 3,
  message_detail: '真的有那么Q弹',
  sender_id: 12,
  is_read: false
}, {
  message_id: 4,
  message_detail: '真的有那么丝滑',
  sender_id: 5,
  is_read: true
}]
const sys_message = [{
  message_id: 11,
  message_detail: '您上传的课程"真的有那么Q弹吗"被退回',
  sender_id: 1,
  is_read: false
}, {
  message_id: 21,
  message_detail: '您上传的课程"真的有那么Q弹吗"审核通过, 发布成功',
  sender_id: 3,
  is_read: false
}, {
  message_id: 31,
  message_detail: '您的评论"xcw我老婆"被举报"引战",遭到管理员删除',
  sender_id: 12,
  is_read: true
}, {
  message_id: 41,
  message_detail: '用户"第一个mt"给您的评论"xcw我老婆"点了个赞',
  sender_id: 5,
  is_read: true
}]
const basic_info = {
  user_name: '第一个mt',
  gender: '男',
  age: 19,
  qq: 1355247243,
  profile: '这个人很懒, 什么都没有留下',
  head_file_path: 'static/image/head_black.png'
}
const important_info = {
  pre_password: '',
  new_password: '',
  re_password: '',
  qq: '',

}
const settings = {
  search_setting: '是',
  my_class_setting: '是',
  my_follow_setting: '是',
  my_star_setting: '是'
}
const fans = [{
  user_id: 1,
  head_file_path: 'static/image/head_black.png',
  user_name: '第二个mt',
  user_profile: '这个人很懒'
}, {
  user_id: 3,
  head_file_path: 'static/image/head_black.png',
  user_name: '第三个mt',
  user_profile: '这个人不懒'
}, {
  user_id: 4,
  head_file_path: 'static/image/head_black.png',
  user_name: '第四个mt',
  user_profile: '这个人真的很懒'
}, {
  user_id: 7,
  head_file_path: 'static/image/head_black.png',
  user_name: '第七个mt',
  user_profile: '这个人不是很懒'
}, {
  user_id: 9,
  head_file_path: 'static/image/head_black.png',
  user_name: '第九个mt',
  user_profile: '这个人确实很懒'
}]
const follow = [{
  user_id: 6,
  head_file_path: 'static/image/head_black.png',
  user_name: '第六个mt',
  user_profile: '这个人真的很懒'
}, {
  user_id: 3,
  head_file_path: 'static/image/head_black.png',
  user_name: '第三个mt',
  user_profile: '这个人不是很懒'
}, {
  user_id: 4,
  head_file_path: 'static/image/head_black.png',
  user_name: '第四个mt',
  user_profile: '这个人很懒'
}, {
  user_id: 5,
  head_file_path: 'static/image/head_black.png',
  user_name: '第五个mt',
  user_profile: '这个人不懒'
}, {
  user_id: 7,
  head_file_path: 'static/image/head_black.png',
  user_name: '第七个mt',
  user_profile: '这个人确实很懒'
}]
const t_header = {
  template: `#header_temp`,
  props: {
    user_name: String,
    is_login: {
      type: Boolean,
      require: true
    }
  },
  data() {
    return {

    }
  },
  methods: {
    to_main_page() {
      window.location = domain.concat('/overViewPage?class_id=', this.class_id);
    },
    to_action() {
      window.location = domain.concat('/userPage');
    }
  }
}
const t_class = {
  template: `#class_temp`,
  props: {
    class_id: {
      type: Number,
      require: true
    },
    class_cover: {
      type: String,
      default: 'static/image/classes/1.jpg'
    },
    class_name: {
      type: String,
      default: '默认课程名'
    }
  },
  data() {
    return {

    }
  },
  methods: {
    to_over_view() {
      window.location = domain.concat('/overViewPage?class_id=', this.class_id);
    }
  }
}
const t_cover_class = {
  template: `#cover_class_temp`,
  props: {
    class_id: {
      type: Number,
      require: true
    },
    class_cover: {
      type: String,
      default: 'static/image/classes/1.jpg'
    },
    class_name: {
      type: String,
      default: '默认课程名'
    },
    select_id: {
      type: Number,
      require: true
    }
  },
  data() {
    return {
      is_select: false
    }
  },
  methods: {
    handle_click() {
      if (this.select_active) {
        this.is_select = !this.is_select;
      } else {
        window.location = domain.concat('/overViewPage?class_id=', this.class_id);
      }
    }
  },
  computed: {
    select_style() {
      return this.is_select ? 'yes' : '';
    },
    select_active() {
      return this.select_id === this.$store.state.select_mode;
    },
    actives() {
      if (this.select_active) {
        return this.$store.state.select_flags;
      }
    }
  },
  watch: {
    // my_style(val) {
    //   if (!val) {
    //     this.is_select = false;
    //   }
    // },
    actives(val) {
      handle_active(val, this, this.class_id);
    }
  }
}
const t_warning_class = {
  template: `#warning_class_temp`,
  props: {
    class_id: {
      type: Number,
      require: true
    },
    class_cover: {
      type: String,
      default: 'static/image/classes/1.jpg'
    },
    class_name: {
      type: String,
      default: '默认课程名'
    },
    is_examine: {
      type: Boolean,
      require: true
    }
  },
  methods: {
    handle_click() {
      window.location = domain.concat('/editPage?class_id=', this.class_id);
    }
  }
}
const t_left_head = {
  template: '#left_head_temp',
  props: {
    user_name: {
      type: String,
      require: true
    },
    head_file_path: {
      type: String,
      default: 'static/image/head_black.png'
    },
    follower_num: {
      type: Number,
      default: 0
    },
    follow_num: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {

    }
  },
  methods: {
    show_fans_panel() {
      this.$emit('show_fans_panel');
    },
    show_follow_panel() {
      this.$emit('show_follow_panel');
    }
  }
}
const t_left_list = {
  template: `#left_list_temp`,
  props: {
    panel_id: {
      type: Number,
      require: true
    },
    list_name: {
      type: String,
      require: true
    },
    icon: {
      type: String,
      require: true
    },
    my_style: {
      type: String,
      default: 'left: 5px'
    }
  },
  methods: {
    show_panel() {
      this.$store.commit('set_panel', this.panel_id);
    }
  },
  computed: {
    is_active() {
      return this.panel_id === this.$store.state.current_panel;
    },
    my_class() {
      return {
        'bgc-d-blue': this.is_active,
        'color-white': this.is_active
      }
    },
    my_css_style() {
      return this.is_active ? '' : 'yes';
    }
  }
}
const t_panel_head = {
  template: `#panel_head_temp`,
  props: {
    head_name: {
      type: Array,
      require: true
    }
  },
  data() {
    return {
      ondrags: []
    }
  },
  methods: {
    handle_click(i) {
      //set style
      this.ondrags = this.ondrags.map((e, index) => {
        if (i === index) {
          return 'yes';
        }
        return '';
      });
      //show sub panel
      this.$emit('switch_sub_panel', i);
    },
    text_left(i) {
      return {
        'left': (i * 165 + 15) + 'px'
      }
    },
    divider_left(i) {
      return {
        'left': (i * 165) + 'px'
      }
    }
  },
  computed: {

  },
  created() {
    for (let e in this.head_name) {
      this.ondrags.push('');
    }
    this.ondrags.splice(0, 1, 'yes');
  }
}
const t_select_bar = {
  template: `#select_bar_temp`,
  props: {
    option: {
      type: String,
      require: true
    },
    select_id: {
      type: Number,
      require: true
    }
  },
  data() {
    return {
      options: ['全选', '反选', '取消选择'],
      is_select_mode: false
    }
  },
  methods: {
    handle_click(i) {
      this.$store.commit('reset_flag');
      new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve()
        }, 1);
      }).then(res => {
        this.$store.commit('empty_list');
        this.$store.commit('set_flag', i);
        if (i === 3) {
          this.$emit('handle_select_axios', this.select_id);
        }
      })
    },
    switch_mode() {
      this.is_select_mode = !this.is_select_mode;
      if (this.is_select_mode) {
        this.$store.commit('switch_mode', this.select_id);
      } else {
        this.$store.commit('set_flag', SELECT_CANCEL_FLAG);
        new Promise((resolve, reject) => {
          setTimeout(() => {
            resolve();
          }, 1)
        }).then(res => {
          this.$store.commit('reset_select');
        })
      }
    }
  },
  created() {
    this.options.push(this.option);
  }
}
const t_user_info = {
  template: `#user_info_temp`,
  props: {
    user_id: {
      type: Number,
      require: true
    },
    head_file_path: {
      type: String,
      default: 'static/image/head_black.png'
    },
    user_name: {
      type: String,
      require: true
    },
    user_profile: {
      type: String,
      default: '这个人很懒'
    }
  },
  data() {
    return {

    }
  },
  methods: {
    to_other_page() {
      window.location = domain.concat('/space/', this.user_id);
    }
  }
}
const t_cover_info = {
  template: `#cover_info_temp`,
  props: {
    user_id: {
      type: Number,
      require: true
    },
    head_file_path: {
      type: String,
      default: 'static/image/head_black.png'
    },
    user_name: {
      type: String,
      require: true
    },
    user_profile: {
      type: String,
      default: '这个人很懒'
    },
    select_id: {
      type: Number,
      require: true
    }
  },
  data() {
    return {
      is_select: false,
    }
  },
  methods: {
    handle_click() {
      if (this.select_active) {
        this.is_select = !this.is_select;
      } else {
        window.location = domain.concat('/space/', this.user_id);
      }
    }
  },
  computed: {
    select_style() {
      return this.is_select ? 'yes' : '';
    },
    select_active() {
      return this.select_id === this.$store.state.select_mode;
    },
    actives() {
      if (this.select_active) {
        return this.$store.state.select_flags;
      }
    }
  },
  watch: {
    actives(val) {
      handle_active(val, this, this.class_id);
    }
  }
}
const t_message = {
  template: `#message_temp`,
  props: {
    message_id: {
      type: Number,
      require: true
    },
    message_detail: {
      type: String,
      default: '/static/image/head_black.jpg'
    },
    sender_id: {
      type: Number,
      require: true
    },
    is_read: {
      type: Boolean,
      require: true
    },
    select_id: {
      type: Number,
      require: true
    }
  },
  data() {
    return {
      is_select: false,
    }
  },
  methods: {
    handle_click() {
      this.is_select = !this.is_select;
    }
  },
  computed: {
    select_style() {
      return this.is_select ? 'yes' : '';
    },
    select_active() {
      return this.select_id === this.$store.state.select_mode;
    },
    actives() {
      if (this.select_active) {
        return this.$store.state.select_flags;
      }
    }
  },
  watch: {
    actives(val) {
      handle_active(val, this, this.message_id);
    }
  }
}

const app = new Vue({
  el: '#app',
  store: store,
  data: {
    user_name: 'diyigemt',
    is_login: true,
    head_file_path: 'static/image/head_black.png',
    follower_num: 0,
    follow_num: 0,
    left_list_data: left_list_data,
    all_classes: all_classes,
    edit_classes: edit_classes,
    all_message: all_message,
    sys_message: sys_message,
    basic_info: basic_info,
    important_info: important_info,
    settings: settings,
    fans: fans,
    follow: follow,
    current_sub_panel: 0,
  },
  methods: {
    handle_select_axios(id) {
      // TODO axios异步请求
      // return new Promise
    },
    switch_sub_panel(i) {
      this.current_sub_panel = i;
    },
    update_basic_info() {
      // TODO update_user_basic_info
    },
    update_important_info() {
      // TODO update_important_info
    },
    show_fans_panel() {
      this.$store.commit('set_panel', 4);
      // TODO axios

    },
    show_follow_panel() {
      this.$store.commit('set_panel', 5);
      // TODO axios
    },
    release_new_class() {
      // TODO release_new_class
    },
    everyday_check_in() {
      // TODO check_in
    },
    user_logout() {
      // TODO logout
    }
  },
  computed: {
    a_user_name() {
      return this.user_name === '' ? '登录' : this.user_name;
    }
  },
  components: {
    t_header: t_header,
    t_class: t_class,
    t_cover_class: t_cover_class,
    t_warning_class: t_warning_class,
    t_left_head: t_left_head,
    t_left_list: t_left_list,
    t_panel_head: t_panel_head,
    t_select_bar: t_select_bar,
    t_user_info: t_user_info,
    t_cover_info: t_cover_info,
    t_message: t_message
  },
  mounted() {
    domain = 'http://'.concat(document.domain);
  }
})