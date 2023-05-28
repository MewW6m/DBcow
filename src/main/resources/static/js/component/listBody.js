/**
 *  * 一覧ボディの描画に関するクラス
 */
export class ListBody {

    /**
     *  * 行を更新する
     *  * @param {jsonObject} json - 更新データ
     */
    updateLines(dataList) {
        $('#listSection tbody').empty();

        $.each(dataList, (i1, line) => {
            let tr = '<tr class="listLine">';
            $.each(line, (i2, col) => {
                tr += '<td class="uk-text-nowrap" data-row="' + col + '"> ' + line[i1] + '</td>';
                tr += '</tr>';
            })
            $('#listSection tbody').append(tr);
        })

        $('#listSection > div').scrollTop(0);
    }

    /**
     *  * 行の色を更新する
     *  * @param {object} elm - 押下した要素
     */
    updateLineColor(elm) {
        $('.clicked-line').removeClass('clicked-line');
        $(elm).addClass('clicked-line');
    }

    /**
     *  * 行の色をリセットする
     */
    removeLineColor() {
        $('.clicked-line').removeClass('clicked-line');
    }

    /* 日時フォーマット(引用：https://zukucode.com/2017/04/javascript-date-format.html) */
    #formatDate(date, format) {
        format = format.replace(/yyyy/g, date.getFullYear());
        format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
        format = format.replace(/dd/g, ('0' + date.getDate()).slice(-2));
        format = format.replace(/HH/g, ('0' + date.getHours()).slice(-2));
        format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
        format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
        format = format.replace(/SSS/g, ('00' + date.getMilliseconds()).slice(-3));
        return format;
    };
}

export let listBody = new ListBody();