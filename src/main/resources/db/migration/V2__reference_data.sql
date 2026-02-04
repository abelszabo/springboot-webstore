-- =========================
-- PAYMENT TYPES
-- =========================

insert into payment_type
(uuid, created_at, updated_at, code, name)
values
    (gen_random_uuid(), now(), now(), 'CARD', 'Bankkártya'),
    (gen_random_uuid(), now(), now(), 'TRANSFER', 'Banki átutalás')
    on conflict (code) do nothing;

-- =========================
-- DELIVERY TYPES
-- =========================

insert into delivery_type
(uuid, created_at, updated_at, code, name)
values
    (gen_random_uuid(), now(), now(), 'COURIER', 'Futárszolgálat'),
    (gen_random_uuid(), now(), now(), 'PICKUP', 'Személyes átvétel')
    on conflict (code) do nothing;
