PGDMP  4    8                |         	   binarfood    16.2    16.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    id character varying(255) NOT NULL,
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
   TABLE DATA                 public          postgres    false    216   X       �          0    16541    orders 
   TABLE DATA                 public          postgres    false    217   #        �          0    16546    product 
   TABLE DATA                 public          postgres    false    218   �!       �          0    16551    users 
   TABLE DATA                 public          postgres    false    219   .$       *           2606    16535    merchant merchant_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.merchant
    ADD CONSTRAINT merchant_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.merchant DROP CONSTRAINT merchant_pkey;
       public            postgres    false    215            ,           2606    32868    order_detail order_detail_pkey 
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
       public          postgres    false    216    218    4656            8           2606    16573 #   product fkk47qmalv2pg906heielteubu7    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkk47qmalv2pg906heielteubu7 FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);
 M   ALTER TABLE ONLY public.product DROP CONSTRAINT fkk47qmalv2pg906heielteubu7;
       public          postgres    false    4650    215    218            6           2606    16558 (   order_detail fkrws2q0si6oyd6il8gqe2aennc    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT fkrws2q0si6oyd6il8gqe2aennc FOREIGN KEY (order_id) REFERENCES public.orders(id);
 R   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT fkrws2q0si6oyd6il8gqe2aennc;
       public          postgres    false    216    4654    217            �   R  x���OO�@��|��h%\M2��X��U������])$(� �O_g%h��D�H��~�yn�6�?��o���=}z�v�ů����M���N��%�c�B(U��ɧ2*�Oϊ��No؏��x;��?~>�y-`�IgZ�LPU8E��c�)N�{�b3���KXQ)�k��R�`�
��r`�Tc('�WLc�3����J��L��4��D�(�2�jb��m��a�v����:DU���C�EK�c"O��G�}���Ic�t�vW@q;�Gl��XSHTH��(Dv	�W(-(��L��}�=�Ӝ~�5;=�K��fnO�dl��'$J��6KjS��b�K"�������d��4ka5�X��K��*0L\&#.�\u0V%��m�����gg���~Kc��*c���6����b.#haG��e:�z�];kᚇA��^t�Ƨ�`O����:˰�ɲMd0��@�{����~�(T(��旅IK���@_	#C�!J�u���H!���aW�7��b���+Ѣ��Δذ�<b�����M>��U,��d��q3J���yr�)��4      �   �  x����j�1F�<ſ���-ɖD�%�&�Z,[�@�%��_e�t�j�O�G����ͷ���������?=��_�+���3����|��?>����^b{��{u�ұTӊO��J)�|�1��%���l k>w�C8+NE��ڬ�]�v��J�9�>~���5d��	�J��D5]�:t,����χ�]WN��ͷ�A��Q:X���(Y�5��6\kB�^�5/+�"��Sm��X���(2 ����&KEU���|��5�X�dT7�a�����b�yR�f�M�,m��L+LS��
��>����+�Kzm�v�9�YhO�1&S��]����:�n)i���9M���%�k����w����B)���h���SGh(��J��b���
�lY,.�V.W���&�9��Ʋ`��u�I��E���KY/�zu�o�@       �   �  x���1k\1 ��~�ۮ��![�l�L2\))���-C������_�l���
����>I>�o��O���/ӏK9}��ym�>M?�=N��������pk	r�q��	�J1�����鼝=p8yZ�_g�=�J��)Hs\"B΄В�fXSHm�=�{�;��&�sP2��dM���A��	�js��hA?�KD��$$ցCn��"i�AC*�*Z��0�\��Hh��{����F���������|(c��Ԁ��	̐,i+��V��>�������da�M.����N�c��Z[-c	-�ĕqnRG\���}-��+� \]�$C�ieB��򟎗U��|�qv���@2Oفi-sCH5�ܱD�ml7����0�p���c��      �   h  x���;o[1������B�����y�M��]�ƈc������&hѡC
��&@<�yqu{rs7\\�}�wu�h��qͻ��~�|r;��$�"s�Cj!�r�����p0	��U�,���(�oz;���g/��k�ń�'����df�?\��D�Y&o��tp�d1BD�CL�N$���<>���J��v�:.�ӏP �m
bc�oE��d��� �WE���K̔��\I5�+ɝ,w�p,����M�5��=A�\�QP�y��uvS�E5���j��6�ENG�<�e��LD�Ko��gт�R���ؚ��D�kR�
����@�s/-�RU��<�Q��Wu,+V��Y�k�;�˱��	J��U����\�!��_���MT�jMy��fW�����Q������[m��hz�N|����{�>���Jt�|%;~X<��(���b���Ы��jT�6�C����My�KS���M�I��uy�Rqv�.��:3����ժn��D�,"�T�\C�TX�K�*�IU'�� i�B�X����U�<M_�~n�oY?/�}3�����MQkg��0�($0%�n�I;���.E��r���S�~F�      �   K  x��S�n1��+���Q{��%���2�H\Fe��3J/QO�D�{�@@}�U^�ޫ�WgW�����������mXN;v�/o.>��/)��(jP^0�����b>ϼa���Ⱜ�mqǸ(��|}p�_��Pb��R��F��T�Jx^ ���0�-n�e��r�٤�Ɩ�z�>�ӧ$�4<���\T��A2!]ԡ`���S�%�,o��b\ʈ�c�SzS�s��h�ǜ�U6Ȅ�4d�'����Gʕf�0�t�O�����6IfIO����rS��k��/�x�x�W_��"V����ʁ�"Y���^�t��;Ŏ���-���3��C���S����)! y&��|���FV*��<7l��m��ݳ6�����4�To��� C(J27\`�
� ��ÅKJ&��~v�q j��mG겉�b�p�)�Yhh6L̴����88H�͎� ��W�2��;rICC�O8�������׼���j���K�"h�rE+#`4D�uLI�eA��:t��n�c��uvmm��n�����Qf+��E�e �b3����v�s��3�1#5Mpp��[�`     