(module
    (func $imported_func (import "imports" "imported_func") (param i32))
    (type $_ (func))
    (type $ii_i (func (param i32 i32) (result i32)))
    (export "add" (func $add))
    (func $add (type $ii_i) (param $0 i32) (param $1 i32) (result i32)
        local.get $0
        local.get $1
        i32.add
    )
    (export "calli" (func $calli))
    (func $calli (type $_)
        i32.const 42
        call $imported_func
    )
    (func $main (type $_)
        i32.const 128
        call $imported_func
    )
    (start $main)
)
