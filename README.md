# 🧭 Product Paging Demo — Jetpack Compose + Paging 3

## Overview

This project demonstrates how to build an **infinite scrolling product list** using **Jetpack Compose**, **Paging 3**, and **Retrofit**.  
It uses the public [DummyJSON API](https://dummyjson.com/docs/products) and focuses on mastering **PagingSource**, **Pager**, and **LoadState handling**.

This project is designed as a **learning sandbox** — starting with a **remote-only paging setup**, then later upgraded to a **Room + RemoteMediator** architecture.

---

## 🎯 Learning Goals

- Understand **how Paging 3 fetches and presents data**.
- Learn how to **connect Pager → Flow → LazyPagingItems** in Compose.
- Handle **load states** for loading, error, and empty UI.
- Avoid common pitfalls like Pager recreation and key reuse.
- Prepare to extend this into an **offline-first (Room) setup** later.

---

## 🧩 Tech Stack

| Layer | Library / Tool |
|-------|---------------|
| UI | Jetpack Compose |
| Pagination | Paging 3 + Paging Compose |
| Networking | Retrofit + OkHttp |
| JSON Parsing | Gson |
| Asynchronous | Kotlin Coroutines + Flow |
| Architecture | Activity-only for learning (can move to MVVM) |
| Backend | [DummyJSON API](https://dummyjson.com/docs/products) |

---

## ⚙️ Project Setup

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/ProductPagingDemo.git
cd ProductPagingDemo

