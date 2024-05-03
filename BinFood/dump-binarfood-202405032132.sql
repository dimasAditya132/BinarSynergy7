PGDMP  5                     |         	   binarfood    16.2    16.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16426 	   binarfood    DATABASE     �   CREATE DATABASE binarfood WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Indonesia.1252';
    DROP DATABASE binarfood;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16529    merchant    TABLE     �   CREATE TABLE public.merchant (
    id uuid NOT NULL,
    merchant_location character varying(255),
    merchant_name character varying(255),
    open boolean NOT NULL
);
    DROP TABLE public.merchant;
       public         heap    postgres    false    4            �            1259    16536    order_detail    TABLE     �   CREATE TABLE public.order_detail (
    id uuid NOT NULL,
    quantity integer NOT NULL,
    total_price double precision NOT NULL,
    order_id uuid NOT NULL,
    product_id uuid NOT NULL
);
     DROP TABLE public.order_detail;
       public         heap    postgres    false    4            �            1259    16541    orders    TABLE     �   CREATE TABLE public.orders (
    id uuid NOT NULL,
    completed boolean NOT NULL,
    destination_address character varying(255),
    order_time timestamp(6) without time zone,
    user_id uuid NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false    4            �            1259    16546    product    TABLE     �   CREATE TABLE public.product (
    id uuid NOT NULL,
    price double precision NOT NULL,
    product_name character varying(255),
    merchant_id uuid NOT NULL,
    name character varying(255)
);
    DROP TABLE public.product;
       public         heap    postgres    false    4            �            1259    16551    users    TABLE     �   CREATE TABLE public.users (
    id uuid NOT NULL,
    email_address character varying(255),
    password character varying(255),
    username character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false    4            �          0    16529    merchant 
   TABLE DATA                 public          postgres    false    215   �       �          0    16536    order_detail 
   TABLE DATA                 public          postgres    false    216   �       �          0    16541    orders 
   TABLE DATA                 public          postgres    false    217   �       �          0    16546    product 
   TABLE DATA                 public          postgres    false    218   �       �          0    16551    users 
   TABLE DATA                 public          postgres    false    219   �       *           2606    16535    merchant merchant_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.merchant
    ADD CONSTRAINT merchant_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.merchant DROP CONSTRAINT merchant_pkey;
       public            postgres    false    215            ,           2606    16540    order_detail order_detail_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT order_detail_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT order_detail_pkey;
       public            postgres    false    216            .           2606    16545    orders orders_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    217            0           2606    16550    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    218            2           2606    16557    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    219            5           2606    16568 "   orders fk32ql8ubntj5uh44ph9659tiih    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk32ql8ubntj5uh44ph9659tiih;
       public          postgres    false    217    4658    219            3           2606    16563 (   order_detail fkb8bg2bkty0oksa3wiq5mp5qnc    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT fkb8bg2bkty0oksa3wiq5mp5qnc FOREIGN KEY (product_id) REFERENCES public.product(id);
 R   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT fkb8bg2bkty0oksa3wiq5mp5qnc;
       public          postgres    false    4656    218    216            6           2606    16573 #   product fkk47qmalv2pg906heielteubu7    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkk47qmalv2pg906heielteubu7 FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);
 M   ALTER TABLE ONLY public.product DROP CONSTRAINT fkk47qmalv2pg906heielteubu7;
       public          postgres    false    218    4650    215            4           2606    16558 (   order_detail fkrws2q0si6oyd6il8gqe2aennc    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT fkrws2q0si6oyd6il8gqe2aennc FOREIGN KEY (order_id) REFERENCES public.orders(id);
 R   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT fkrws2q0si6oyd6il8gqe2aennc;
       public          postgres    false    4654    216    217            �   �   x���OK�0�{?EnUp$���xR�CAW�]=O&�*�"u��݂.�m�o������y�vx�%N�t����/���M/.jm�do�k���d��䓊Ҙ�J�O�p��K�Gq,�K�˛�;G�R��6.e�u T.@ȁ��m�A������v,2N_gs2Sb�����A��Bn�OLVGE+r�s*��eN��qsK&[0�*�AZ�p����S\��G�N���Ǫ�o b��      �   
   x���          �   
   x���          �   �   x���KKC1����E�#��M&�R(rA�`��$3#�GK���[Dp)�;��L�f��5Ӽ}0�S{����qϧ�e�o��sazM<s�����K����F�+�W���sg��G�xYR���Q �.k��%RLXZ#w����a��F0�����qRF^γ�p֌?������CUK!�� ���@��@IB��3�j�L%      �     x���MK1���{[��l��`łV��I&�Bז����cO�O���d�X͖�j�X�W�S�m��4�~�>_7�UuSgnf�`�BЙ���`d1:t�]U��mG�J�I������U�BVe��l.�M`�"p���MjTl�����>;��i��l�ic�^���9[cQ;@�6�1�si�4IY_4:���B��/�_2���x���I�Z3��qY]!��S�qOo��B�S?�_Z�|�LBϒ_���������FeDl$��F��$�u���
�I&���a0��     