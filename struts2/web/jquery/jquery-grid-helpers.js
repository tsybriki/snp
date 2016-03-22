/**
 * Edit user link formatter - creates link to edit user profile.
 * @param cellvalue Strign value located in cell
 * @param options url 
 * @param rowObject
 */
function editUserLinkFormatter ( cellvalue, options, rowObject ){
    var userId = rowObject.userId;
    var op = {url:options.url };
    var targetUrl = "";
    if(!isUndefined(options.colModel.formatoptions)) {
        op = $.extend({},op,options.colModel.formatoptions);
    }
    if(op.url) {targetUrl = op.url + '?id=' + userId;}
    if(!isEmpty(cellvalue)) {
        return "<a href=\"#\" onclick=\"" + "displayContents('" + targetUrl + "');\">" + cellvalue + "</a>";
    }else {
        return $.fn.fmatter.defaultFormat(cellvalue, options);
    }
};