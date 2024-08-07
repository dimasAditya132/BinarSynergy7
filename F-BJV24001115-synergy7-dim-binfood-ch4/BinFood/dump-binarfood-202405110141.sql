PGDMP      )                |         	   binarfood    16.2    16.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    status boolean NOT NULL
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
    destination_address character varying(255),
    order_time timestamp(6) without time zone,
    user_id uuid NOT NULL,
    status boolean NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false    4            �            1259    16546    product    TABLE     �   CREATE TABLE public.product (
    id uuid NOT NULL,
    product_price double precision NOT NULL,
    product_name character varying(255),
    merchant_id uuid NOT NULL
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
   TABLE DATA                 public          postgres    false    215   �       �          0    16536    order_detail 
   TABLE DATA                 public          postgres    false    216   �       �          0    16541    orders 
   TABLE DATA                 public          postgres    false    217   �       �          0    16546    product 
   TABLE DATA                 public          postgres    false    218   �       �          0    16551    users 
   TABLE DATA                 public          postgres    false    219   �       *           2606    16535    merchant merchant_pkey 
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
       public            postgres    false    218            2           2606    32790 "   product ukoluakrvn8an7ugtld812pcq9 
   CONSTRAINT     h   ALTER TABLE ONLY public.product
    ADD CONSTRAINT ukoluakrvn8an7ugtld812pcq9 UNIQUE (id, merchant_id);
 L   ALTER TABLE ONLY public.product DROP CONSTRAINT ukoluakrvn8an7ugtld812pcq9;
       public            postgres    false    218    218            4           2606    16557    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    219            7           2606    16568 "   orders fk32ql8ubntj5uh44ph9659tiih    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk32ql8ubntj5uh44ph9659tiih;
       public          postgres    false    4660    217    219            5           2606    16563 (   order_detail fkb8bg2bkty0oksa3wiq5mp5qnc    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT fkb8bg2bkty0oksa3wiq5mp5qnc FOREIGN KEY (product_id) REFERENCES public.product(id);
 R   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT fkb8bg2bkty0oksa3wiq5mp5qnc;
       public          postgres    false    218    4656    216            8           2606    16573 #   product fkk47qmalv2pg906heielteubu7    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkk47qmalv2pg906heielteubu7 FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);
 M   ALTER TABLE ONLY public.product DROP CONSTRAINT fkk47qmalv2pg906heielteubu7;
       public          postgres    false    218    215    4650            6           2606    16558 (   order_detail fkrws2q0si6oyd6il8gqe2aennc    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT fkrws2q0si6oyd6il8gqe2aennc FOREIGN KEY (order_id) REFERENCES public.orders(id);
 R   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT fkrws2q0si6oyd6il8gqe2aennc;
       public          postgres    false    217    216    4654            �   �   x���?O�0�=��[@���v�R�HP)0���"��O=P��.����n؎����G�U��Aן��;.{�z{���E�����"�`-u����|RQ�^��	.SMo��e�a���i��hsO&[0�*�AZ�p����S�Ǝ'�O��~��t��\�,� �\��K'�������	v��d�ĖX'�����%������q�K*9�%U-��]���]���      �   �   x�˽
�0 �O�-
��\�䂓C��T��U�s�BA)�����8��m�\����5w����,���&��}��A:��8M��ؤ2P-�w�t �e+L+�+�Z!="+�
dR�!DD��ǔ�������
��#�Y[�5�*���9���xj��2 ,�      �   �   x����J1��<Eo�`�4I;�x�Y�Y���FF���zQO!����t���G3�O��Χ�_�\�w��������.e�a�	|�$� �b[/��Ttwgv�z^���ЁEC<:{�x=�I$+�l=Hbd�4�Kp!���v)�����W�N�X�h!jh6aq�$,���Y�vd�z�J��H+���T3Dd��5I�!'�m *ZW��
��F#	N�Rm�A~���B������z*      �   �   x���MKA�{��V���wO*E��]��L2RZݲ��ߑ^<Vr��y�ow��A���Y��|ܗ��<�R������f�������9�+��JA�)Q�\Y{��J"�U����q���m��j��kK�!u �J�SΤ���U�D�Y6^�X�q"H����ư��W�ϒ����BcM�V�:�е"&�p����X�$6��_���(�$���(9e�n��d��2�9����Z� ��p1      �     x����KA���+��
�lv2�+���zO&3�е�m�w�IP�!��ޏ7^��j��z�>�����q��}�v��:[Vu�6cRV%FA#X��^P<�������=��転�����_D��LBƥd;��1ްPjbc�-��N�p�Y���N�/�H#�|(z�}?�)9�L>��z �N��(��Œ�sϿ!�x*��pMK�NǞ$���3h`ѱb�AKd��q��/�A[�<v��5@�Z��[�M4.dB�>0����bi��tz��iٓ����     