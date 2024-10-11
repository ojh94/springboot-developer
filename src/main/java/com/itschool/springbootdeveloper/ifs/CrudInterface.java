package com.itschool.springbootdeveloper.ifs;

import com.itschool.springbootdeveloper.network.Header;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
