var domain = '';
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
const t_notice = {
  template: `#notice_temp`,
  props: {
    notice_id: {
      type: Number,
      require: true
    },
    index: {
      type: Number,
      require: true
    },
    notice_name: {
      type: String,
      require: true
    }
  },
  data() {
    return {

    }
  },
  methods: {
    show_notice() {
      this.$emit('show_notice', this.index);
    }
  },
  computed: {
    my_name() {
      return this.index + '. ' + this.notice_name;
    }
  }
}
const t_rank = {
  template: `#rank_temp`,
  props: {
    class_id: {
      type: Number,
      require: true
    },
    index: {
      type: Number,
      require: true
    },
    class_name: {
      type: String,
      require: true
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
const t_head = {
  template: `#head_temp`,
  props: {
    head_name: {
      type: String,
      require: true
    },
    index: {
      type: Number,
      require: true
    }
  },
  data() {
    return {

    }
  },
  methods: {
    to_more() {
      this.$emit('to_more', this.index);
    }
  }
}
const app = new Vue({
  el: '#app',
  data: {
    user_name: '',
    is_login: false,
    recommend_classes: [{
      class_id: 1,
      class_cover: 'static/image/classes/1.jpg',
      class_name: '操作系统原理'
    }, {
      class_id: 2,
      class_cover: 'static/image/classes/1.jpg',
      class_name: '操作系统原理'
    }],
    announcement: [{
      notice_id: 1,
      notice_name: '2020.6.25停机维护公告'
    }, {
      notice_id: 2,
      notice_name: '2020.6.19停机维护公告'
    }],
    rank_classes: [{
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
    }, {
      class_id: 4,
      class_cover: 'static/image/classes/1.jpg',
      class_name: '操作系统原理'
    }],
    all_classes: [[{
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
    }, {
      class_id: 4,
      class_cover: 'static/image/classes/1.jpg',
      class_name: '操作系统原理'
    }], [{
      class_id: 5,
      class_cover: 'static/image/classes/1.jpg',
      class_name: '操作系统原理'
    }, {
      class_id: 6,
      class_cover: 'static/image/classes/1.jpg',
      class_name: '操作系统原理'
    }, {
      class_id: 7,
      class_cover: 'static/image/classes/1.jpg',
      class_name: '操作系统原理'
    }]]
  },
  methods: {
    // get_splice_classes() {
    //   let res = [];
    //   for (let i = 0; i < this.all_classes.length; i += 4) {
    //     res.push(this.all_classes.splice(i, (i + 4)));
    //   }
    //   return res;
    // }
  },
  computed: {
    a_user_name() {
      return this.user_name === '' ? '登录' : '欢迎'.concat(this.user_name)
    }
  },
  components: {
    t_header: t_header,
    t_class: t_class,
    t_notice: t_notice,
    t_rank: t_rank,
    t_head: t_head
  },
  mounted() {
    domain = 'http://'.concat(document.domain);
    let params = new FormData();
    params.append('page', 1);
    params.append('size', 4);
    axios.get(domain.concat('/courses/recommendations'), params, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then((res) => {
      if (res.data.retCode === 0) {
        console.log(res);
      }
    }).catch((err) => {

    })
  }
})