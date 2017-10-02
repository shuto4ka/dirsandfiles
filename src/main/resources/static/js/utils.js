function formatBytes(bytes) {
    var sizes = ['Bytes', 'Kb', 'Mb', 'Gb', 'Tb'];
    var sizeIndex = 0;
    var currentSize = bytes;
    while (currentSize > 1024 && sizeIndex < sizes.length - 1) {
        currentSize = currentSize / 1024;
        sizeIndex++;
    }
    return (Math.round(currentSize * 100) / 100) + sizes[sizeIndex];
}

function getDirAndDateTimeById(dataTable, id) {
    for (var i = 0; i < dataTable.rows().data().length; i++) {
        if (id === dataTable.cell(i, 0).data()) {
            return {
                dateTime: dataTable.cell(i, 1).data(),
                dir: dataTable.cell(i, 2).data()
            }
        }
    }
    return null;
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = JSON.parse(jqXHR.responseText);
    failedNote = new Noty({
        text: "<span class='glyphicon glyphicon-exclamation-sign'></span> &nbsp;" + errorInfo.error + "<br>" + errorInfo.message,
        type: "error",
        layout: "bottomRight",
        timeout: 3000
    }).show();
}

function compareDatesAsc(a, b) {
    var x = createDateFromStr(a),
        y = createDateFromStr(b);
    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
}

function compareDatesDesc(a, b) {
    var x = createDateFromStr(a),
        y = createDateFromStr(b);
    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
}

function createDateFromStr(str) {
    //dd.MM.yyyy HH:mm
    return new Date(str.substr(6, 4), str.substr(3, 2), str.substr(0, 2),
        str.substr(11, 2), str.substr(14, 2));
}
