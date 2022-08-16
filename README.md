# NewsAnnouncer

Simple plugin that allows you to display news to users at login (via the chat). Commands exist in game to add, edit,
delete, enable, disable and list all news items.

Each news item has a number of times to display to users set individually. This value is tracked per user, so 
that each user will be shown that news item the amount of times defined.

There is a single permission 'news' that grants access to manage news and is given to ops by default.

Commands:

        create usage: /news create <# of times show> <news message>
        delete usage: /news delete <id#>
        enable usage: /news enable <id#>
        disable usage: /news disable <id#>
        edit usage: /news edit <id-#> <# of times show> <news message>
        list usage: /news list <page-#>