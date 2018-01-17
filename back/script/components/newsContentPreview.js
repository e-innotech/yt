$(function () {
    var E = window.wangEditor;
    var editor = new E('','#editor');
    editor.create();
    editor.$textElem.attr('contenteditable', false);
    editor.txt.html(newsContent);
});