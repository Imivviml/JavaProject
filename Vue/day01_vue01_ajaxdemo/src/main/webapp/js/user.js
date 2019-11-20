new Vue({

    el:"#app",
    data:{
        user:{
            id:"",
            username:"",
            password:"",
            email:"",
            age:"",
            sex:""
        },
        userList:[]
    },
    methods:{
        findAll:function () {
            //在方法中定义一个变量,表明是vue的this对象
            var _this = this;
            axios.get("/day01_vue01_ajaxdemo_war_exploded/user/findAll.do")
                .then(function (response) {
                    _this.userList = response.data;
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        findById:function (userId) {
            //在方法中定义一个变量,表明是vue的this对象
            var _this = this;
            axios.get("/day01_vue01_ajaxdemo_war_exploded/user/findById.do",{params:{id:userId}})
                .then(function (response) {
                    _this.user = response.data;
                    $("#myModal").modal("show");
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        update:function (user) {//post请求
            //在方法中定义一个变量,表明是vue的this对象
            var _this = this;
            axios.post("/day01_vue01_ajaxdemo_war_exploded/user/updateUser.do",_this.user)
                .then(function (response) {
                    _this.findAll();
                })
                .catch(function (error) {
                    console.log(error);
                })
        }
    },
    created:function () {//当我们页面加载的时候触发请求,查询所有
        this.findAll();
    }

});