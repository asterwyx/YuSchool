var domain = 123;
$(function() {
    domain = 'http://' + document.domain + '/YuSchool';
});
function to_my_page() {
    window.location = domain + '/userPage';
}
function to_main_page() {
    window.location = domain + '/mainPage';
}
function show_class_over_view(o) {
    let id = $(o).attr("course_id");
    window.location = domain + "/overViewPage?course_id=" + id;
}
function show_other_page(o) {
    let id = $(o).attr("user_id");
    window.location =  domain + "/otherPage?user_id=" + id;
}
function show_class_edit(o) {
    let id = $(o).attr("course_id");
    window.location = domain + "/classEditPage?course_id=" + id;
}