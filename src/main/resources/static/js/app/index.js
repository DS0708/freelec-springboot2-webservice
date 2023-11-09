var main = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function (){
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

    },
    save : function (){
         //alert('저장 버튼 클릭');
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){ //AJAX 요청이 성공했을 때 실행되는 콜백 함수
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).failIfMajorPerformanceCaveat(function (){ //AJAX 요청이 실패하거나 주요한 성능 문제가 발생했을때 실행되는 콜백 함수
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: "/api/v1/posts/"+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).failIfMajorPerformanceCaveat(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: "/api/v1/posts/"+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).failIfMajorPerformanceCaveat(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
/*
* 다른 JS와 init, save이름이 겹칠 수 있으므로 index.js만의 Scope를 만들어서 사용
* */

main.init() //이벤트 리스너 설정