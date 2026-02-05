-- =========================
-- PAYMENT TYPES
-- =========================

insert into payment_type
(uuid, version, created_at, updated_at, created_by, code, name)
values
    (gen_random_uuid(), 1, now(), now(), '36c9b365-b879-4a97-ae8f-4cb8b1b37042', 'CARD', 'Bankkártya'),
    (gen_random_uuid(), 1, now(), now(), '36c9b365-b879-4a97-ae8f-4cb8b1b37042', 'TRANSFER', 'Banki átutalás')
    on conflict (code) do nothing;

-- =========================
-- DELIVERY TYPES
-- =========================

insert into delivery_type
(uuid, version, created_at, updated_at, created_by, code, name)
values
    (gen_random_uuid(), 1, now(), now(), '36c9b365-b879-4a97-ae8f-4cb8b1b37042', 'COURIER', 'Futárszolgálat'),
    (gen_random_uuid(), 1, now(), now(), '36c9b365-b879-4a97-ae8f-4cb8b1b37042', 'PICKUP', 'Személyes átvétel')
    on conflict (code) do nothing;
